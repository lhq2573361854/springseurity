package com.tianling.springsecurity.validate.Impl;

import cn.hutool.core.util.StrUtil;
import com.tianling.springsecurity.entities.ImageCode;
import com.tianling.springsecurity.entities.ValidateCode;
import com.tianling.springsecurity.enums.ValidateCodeType;
import com.tianling.springsecurity.exception.except.ValidateCodeException;
import com.tianling.springsecurity.properties.SecurityProperties;
import com.tianling.springsecurity.validate.ValidateCodeProcessor;
import com.tianling.springsecurity.validate.ValidateCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/23 22:58
 */
@Slf4j
public abstract class AppAbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy =  new HttpSessionSessionStrategy();

    @Autowired
    protected AbstractValidateCodeGenerator validateCodeGeneratorImpl;

    @Autowired
    ValidateCodeRepository validateCodeRepository;

    @Autowired
    protected SecurityProperties securityProperties;

    @Override
    public  void create(ServletWebRequest request) throws Exception {

        C generate = generate();
        save(request,generate);
        send(request,generate);
    }

    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType validateCodeType = getValidateCodeType(request);
        C codeInSession = (C) validateCodeRepository.get(request,validateCodeType);

        String codeInRequest = "";
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),validateCodeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            e.printStackTrace();
        }

        if (StrUtil.isBlank(codeInRequest)) {
            throw new ValidateCodeException( "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpired()) {
            validateCodeRepository.remove(request,getValidateCodeType(request));
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StrUtil.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        validateCodeRepository.remove(request,getValidateCodeType(request));
    }

    private void save(ServletWebRequest request, C validateCode){
        ValidateCode validateCode1 = new ValidateCode(validateCode.getCode(),validateCode.getExpireTime());
        validateCodeRepository.save(request,validateCode1,getValidateCodeType(request));
        // sessionStrategy.setAttribute(request, getSessionKey(request), validateCode1);
    }

    private ValidateCodeType getValidateCodeType(ServletWebRequest request){
        String type = StrUtil.subBefore(getClass().getSimpleName(),"ValidateCodeProcessor",false);
        return  ValidateCodeType.valueOf(type.toUpperCase());
    }

    private String getSessionKey(ServletWebRequest request) {
        return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
    }

    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    private C generate() {
        ImageCode imageCode = new ImageCode(validateCodeGeneratorImpl.getImage(), validateCodeGeneratorImpl.getText(),securityProperties.getCode().getImage().getExpireIn());
        log.info("验证码为："+validateCodeGeneratorImpl.getText());
        return (C)imageCode;
    }

}

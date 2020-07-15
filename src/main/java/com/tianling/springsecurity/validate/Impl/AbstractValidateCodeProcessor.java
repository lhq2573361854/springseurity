package com.tianling.springsecurity.validate.Impl;

import cn.hutool.core.util.StrUtil;
import com.tianling.springsecurity.validate.ValidateCodeProcessor;
import com.tianling.springsecurity.enums.ValidateCodeType;
import com.tianling.springsecurity.entities.ImageCode;
import com.tianling.springsecurity.entities.ValidateCode;
import com.tianling.springsecurity.exception.except.ValidateCodeException;
import com.tianling.springsecurity.properties.SecurityProperties;
import com.tianling.springsecurity.validate.ValidateCodeRepository;
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
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy =  new HttpSessionSessionStrategy();

    @Autowired
    protected AbstractValidateCodeGenerator validateCodeGeneratorImpl;

    @Autowired
    ValidateCodeRepository validateCodeRepository;

    @Autowired
    protected SecurityProperties securityProperties;

    @Override
    public  void create(ServletWebRequest request) throws Exception {

        C generate = generate(request);
        save(request,generate);
        send(request,generate);
    }

    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType processorType = getValidateCodeType(request);

        String sessionKey = getSessionKey(request);

        C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);

        String codeInRequest = null;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),processorType.getParamNameOnValidate());
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
            sessionStrategy.removeAttribute(request, sessionKey);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StrUtil.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, sessionKey);
    }

    private void save(ServletWebRequest request, C validateCode){
        ValidateCode validateCode1 = new ValidateCode(validateCode.getCode(),validateCode.getExpireTime());
        sessionStrategy.setAttribute(request, getSessionKey(request), validateCode1);
    }

    private ValidateCodeType getValidateCodeType(ServletWebRequest request){
        String type = StrUtil.subBefore(getClass().getSimpleName(),"ValidateCodeProcessor",false);
        return  ValidateCodeType.valueOf(type.toUpperCase());
    }

    private String getSessionKey(ServletWebRequest request) {
        return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
    }

    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    private C generate(ServletWebRequest request) {
        ImageCode imageCode = new ImageCode(validateCodeGeneratorImpl.getImage(), validateCodeGeneratorImpl.getText(),securityProperties.getCode().getImage().getExpireIn());
        return (C)imageCode;
    }

}

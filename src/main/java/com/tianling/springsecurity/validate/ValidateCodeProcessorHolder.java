package com.tianling.springsecurity.validate;

import cn.hutool.core.util.StrUtil;
import com.tianling.springsecurity.validate.Impl.AbstractValidateCodeGenerator;
import com.tianling.springsecurity.enums.ValidateCodeType;
import com.tianling.springsecurity.exception.except.ValidateCodeException;
import com.tianling.springsecurity.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/24 10:40
 */
@Component
public class ValidateCodeProcessorHolder {
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;
    @Autowired
    SecurityProperties securityProperties;
    @Autowired
    AbstractValidateCodeGenerator validateCodeGeneratorImpl;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        setValidateCodeByProperties(type);
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
          if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }

        return processor;
    }

    private void setValidateCodeByProperties(String type){
        if(StrUtil.equalsIgnoreCase(type,"image")){
            validateCodeGeneratorImpl.setWidth(securityProperties.getCode().getImage().getWidth());
            validateCodeGeneratorImpl.setHeight(securityProperties.getCode().getImage().getHeight());
            validateCodeGeneratorImpl.setImageType(securityProperties.getCode().getImage().getImageType());
            validateCodeGeneratorImpl.setExpireIn(securityProperties.getCode().getImage().getExpireIn());
            validateCodeGeneratorImpl.setLength(securityProperties.getCode().getImage().getLength());
        }
        if(StrUtil.equalsIgnoreCase(type,"sms")){
            validateCodeGeneratorImpl.setLength(securityProperties.getCode().getSms().getLength());
        }
    }
}

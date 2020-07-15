package com.tianling.springsecurity.validate.sms;

import com.tianling.springsecurity.entities.ValidateCode;
import com.tianling.springsecurity.enums.SecurityConstants;
import com.tianling.springsecurity.validate.Impl.AppAbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/24 10:19
 */
@Component
public class SmsValidateCodeProcessor extends AppAbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String defaultParameterNameMobile = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getStringParameter(request.getRequest(), defaultParameterNameMobile);
        smsCodeSender.send(mobile,validateCode.getCode());
    }

}

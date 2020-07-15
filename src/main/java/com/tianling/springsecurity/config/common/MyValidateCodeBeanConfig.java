package com.tianling.springsecurity.config.common;

import com.tianling.springsecurity.authentication.MyLogoutSuccessHandler;
import com.tianling.springsecurity.properties.SecurityProperties;
import com.tianling.springsecurity.session.MyExpiredSessionStrategy;
import com.tianling.springsecurity.session.MyInvalidSessionStrategy;
import com.tianling.springsecurity.validate.Impl.AbstractValidateCodeGenerator;
import com.tianling.springsecurity.validate.Impl.ValidateCodeGeneratorImpl;
import com.tianling.springsecurity.validate.sms.DefaultSmsCodeSender;
import com.tianling.springsecurity.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/23 12:04
 */
@Configuration
public class MyValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean
    public AbstractValidateCodeGenerator getValidateCodeGenerator(){
        ValidateCodeGeneratorImpl validateCodeGeneratorImpl = new ValidateCodeGeneratorImpl();
        return validateCodeGeneratorImpl;
    }


    @Bean
    @ConditionalOnMissingBean
    public SmsCodeSender getSmsCodeSender(){
        return new DefaultSmsCodeSender();
    }

    @Bean
    //用户可以通过实现一个InvalidSessionStrategy类型的bean来覆盖掉默认的实现--》NRSCInvalidSessionStrategy
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new MyInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new MyExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean
    public LogoutSuccessHandler getLogoutSuccessHandler(){
        return new MyLogoutSuccessHandler(securityProperties.getBrowser().getOut());
    }
}

package com.tianling.springsecurity.config.app;

import com.tianling.springsecurity.enums.SecurityConstants;
import com.tianling.springsecurity.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/4 23:33
 */
@Component
public class MyAuthorizationConfigProvider implements AuthorizationConfigProvider {
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                securityProperties.getBrowser().getLoginPage(),
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                securityProperties.getBrowser().getLoginPage(),
                securityProperties.getBrowser().getSignUpUrl(),
                "/authentication/register",
                "connect/weixin",
                "/social/signUp ",
                securityProperties.getBrowser().getOut()
        ).permitAll();
    }

}

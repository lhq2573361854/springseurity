package com.tianling.springsecurity.config.app;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/4 23:30
 */
public interface AuthorizationConfigManager {
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}

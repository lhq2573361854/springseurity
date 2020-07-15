package com.tianling.springsecurity.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/4 23:33
 */
@Component
public class MyAuthorizationConfigManager implements AuthorizationConfigManager {
    @Autowired
    private List<AuthorizationConfigProvider> providers;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizationConfigProvider provider : providers) {
            provider.config(config);
        }
        config.anyRequest().authenticated();
    }
}

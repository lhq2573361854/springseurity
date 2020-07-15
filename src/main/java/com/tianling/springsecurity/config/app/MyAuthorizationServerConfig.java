package com.tianling.springsecurity.config.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/30 23:35
 */
@Configuration
@EnableAuthorizationServer
@Slf4j
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    UserDetailsService myUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenStore jwtTokenStore;

    @Autowired(required = false)
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    TokenEnhancer myTokenEnhancer;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("tianling").secret(passwordEncoder.encode("123456"))
                .accessTokenValiditySeconds(3600)
                .authorizedGrantTypes("password","refresh_token")
                .scopes("all","read","write")
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore)
                .authenticationManager(authenticationManager).userDetailsService(myUserDetailsService);
        if (jwtAccessTokenConverter != null && myTokenEnhancer!=null ) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            ArrayList<TokenEnhancer> tokenEnhancers = new ArrayList<>();
            tokenEnhancers.add(myTokenEnhancer);
            tokenEnhancers.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
            log.info("希望不是一样的"+myTokenEnhancer);
            log.info("希望不是一样的"+jwtAccessTokenConverter);
            endpoints.tokenEnhancer(tokenEnhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        }

    }
}


package com.tianling.springsecurity.config.common;

import com.tianling.springsecurity.properties.SecurityProperties;
import com.tianling.springsecurity.social.SocialAuthenticationFilterPostProcessor;
import com.tianling.springsecurity.social.jdbc.JdbcUsersConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/27 23:00
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired(required = false)
    ConnectionSignUp connectionSignUp;
    @Autowired(required = false)
    SocialAuthenticationFilterPostProcessor authenticationFilterPostProcessor;

    @Primary
    @Bean
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        if (connectionSignUp != null) {
            jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return jdbcUsersConnectionRepository;
    }

    @Bean
    public SpringSocialConfigurer getSocialSecurityConfig(){
        MySpringSocialConfigurer springSocialConfigurer = new MySpringSocialConfigurer(securityProperties.getSocial().getFilterProcessesUrl());
        springSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        if (authenticationFilterPostProcessor != null) {
            springSocialConfigurer.setSocialAuthenticationFilterPostProcessor(authenticationFilterPostProcessor);
        }
        return springSocialConfigurer;
    }

    @Bean
    public ProviderSignInUtils getProviderSignInUtils(ConnectionFactoryLocator locator){
        return new ProviderSignInUtils(locator,getUsersConnectionRepository(locator));
    }

}

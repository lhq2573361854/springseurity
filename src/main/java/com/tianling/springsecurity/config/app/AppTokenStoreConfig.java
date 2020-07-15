package com.tianling.springsecurity.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/4 15:00
 */
@Configuration
public class AppTokenStoreConfig {
    @Autowired
    RedisConnectionFactory redisConnection;
//    @Bean
//    public TokenStore redisTokenStore(){
//        return new RedisTokenStore(redisConnection);
//    }

    @Configuration
    public static  class JwtTokenConfig{
        @Bean
        public TokenStore JwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            jwtAccessTokenConverter.setSigningKey("tian");
            return jwtAccessTokenConverter;
        }

        @Bean
        public TokenEnhancer myTokenEnhancer(){
            return new MyTokenEnhancer();
        }
    }
}

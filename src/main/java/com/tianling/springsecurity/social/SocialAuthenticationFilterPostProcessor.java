package com.tianling.springsecurity.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/4 0:26
 */
public interface SocialAuthenticationFilterPostProcessor {
    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}

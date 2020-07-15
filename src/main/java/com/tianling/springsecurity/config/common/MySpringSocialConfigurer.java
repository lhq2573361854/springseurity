package com.tianling.springsecurity.config.common;

import com.tianling.springsecurity.social.SocialAuthenticationFilterPostProcessor;
import lombok.Data;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/28 22:29
 */
@Data
public class MySpringSocialConfigurer extends SpringSocialConfigurer {
    private String filterProcessesUrl;
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    public MySpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }



    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter t = (SocialAuthenticationFilter)super.postProcess(object);
        if (socialAuthenticationFilterPostProcessor != null) {
            socialAuthenticationFilterPostProcessor.process(t);
        }
        t.setFilterProcessesUrl(filterProcessesUrl);
        return (T) t;
    }
}

package com.tianling.springsecurity.config.brower;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author TianLing
 * Date 2020/6/4 15:39
 * Description
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(new TimeInterceptor());
    }
}

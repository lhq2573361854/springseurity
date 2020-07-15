package com.tianling.springsecurity.config.common;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/4 13:45
 */
@Component
public class SpringSocialConfigurerBeanProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StrUtil.equals(beanName,"getSocialSecurityConfig")){
            MySpringSocialConfigurer bean1 = (MySpringSocialConfigurer) bean;
            bean1.signupUrl("/social/signin");
            return bean1;
        }
        return bean;
    }
}

package com.tianling.springsecurity.social.qq.config;

import com.tianling.springsecurity.config.common.SocialAutoConfigurerAdapter;
import com.tianling.springsecurity.properties.QQProperties;
import com.tianling.springsecurity.properties.SecurityProperties;
import com.tianling.springsecurity.social.qq.connect.QQConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/28 0:21
 */
@Configuration
@ConditionalOnProperty(prefix = "my.security.social.qq",name = "app-id")
@Slf4j
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        log.info(qq.toString());
        return new QQConnectionFactory(qq.getProviderId(),qq.getAppId(),qq.getAppSecret());
    }

}

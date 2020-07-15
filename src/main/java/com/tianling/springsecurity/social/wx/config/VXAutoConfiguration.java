package com.tianling.springsecurity.social.wx.config;

import com.tianling.springsecurity.config.common.SocialAutoConfigurerAdapter;
import com.tianling.springsecurity.properties.SecurityProperties;
import com.tianling.springsecurity.properties.VXProperties;
import com.tianling.springsecurity.social.MyConnectView;
import com.tianling.springsecurity.social.wx.connect.VXConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/30 0:30
 */
@Configuration
@ConditionalOnProperty(prefix = "my.security.social.vx", name = "app-id")
public class VXAutoConfiguration extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        VXProperties vx = securityProperties.getSocial().getVx();
        return new VXConnectionFactory(vx.getProviderId(),vx.getAppId(),vx.getAppSecret());
    }
    @Bean("connect/weixinConnected")
    @ConditionalOnMissingBean(name = "weixinConnectView")
    public View VXConnectView(){
        return new MyConnectView();
    }
}

package com.tianling.springsecurity.social.qq.connect;

import com.tianling.springsecurity.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/27 22:53
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }

}

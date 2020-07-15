package com.tianling.springsecurity.social.qq.connect;

import com.tianling.springsecurity.social.qq.api.QQ;
import com.tianling.springsecurity.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/27 22:16
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;
    public static final String URL_AUTHORIZATION = "https://graph.qq.com/oauth2.0/authorize";
    public static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId,String appSecret) {
        super(new QQAuth2Template(appId,appSecret,URL_AUTHORIZATION,URL_ACCESS_TOKEN));
        this.appId = appId;
    }


    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}

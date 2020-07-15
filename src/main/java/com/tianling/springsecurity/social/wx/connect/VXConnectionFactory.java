package com.tianling.springsecurity.social.wx.connect;

import com.tianling.springsecurity.social.wx.api.VX;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/30 0:14
 */
public class VXConnectionFactory extends OAuth2ConnectionFactory<VX> {

    public VXConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new VXServiceProvider(appId, appSecret), new VXAdapter());
    }

    /**
     * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if(accessGrant instanceof VXAccessGrant) {
            return ((VXAccessGrant)accessGrant).getOpenId();
        }
        return null;
    }

    public Connection<VX> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<VX>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    public Connection<VX> createConnection(ConnectionData data) {
        return new OAuth2Connection<VX>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    private ApiAdapter<VX> getApiAdapter(String providerUserId) {
        return new VXAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<VX> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<VX>) getServiceProvider();
    }



}

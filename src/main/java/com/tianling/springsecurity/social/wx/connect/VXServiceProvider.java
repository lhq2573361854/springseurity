package com.tianling.springsecurity.social.wx.connect;

import com.tianling.springsecurity.social.wx.api.VX;
import com.tianling.springsecurity.social.wx.api.VXImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/29 23:17
 */
public class VXServiceProvider extends AbstractOAuth2ServiceProvider<VX> {

    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";


    public VXServiceProvider(String appId, String appSecret) {
        super(new VXOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public VX getApi(String accessToken) {
        return new VXImpl(accessToken);
    }
}

package com.tianling.springsecurity.social.wx.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/29 23:12
 */

public class VXAccessGrant extends AccessGrant {


    private String openId;

    public VXAccessGrant() {
        super("");
    }

    public VXAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}

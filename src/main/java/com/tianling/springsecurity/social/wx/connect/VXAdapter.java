package com.tianling.springsecurity.social.wx.connect;

import com.tianling.springsecurity.social.wx.api.VX;
import com.tianling.springsecurity.social.wx.api.VXUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/29 23:47
 */
public class VXAdapter implements ApiAdapter<VX> {
    private String openId;

    public VXAdapter() {
    }

    public VXAdapter(String openId){
        this.openId = openId;
    }

    @Override
    public boolean test(VX api) {
        return true;
    }

    @Override
    public void setConnectionValues(VX api, ConnectionValues values) {
        VXUserInfo userInfo = api.getUserInfo(openId);

        values.setDisplayName(userInfo.getNickname());
        values.setProviderUserId(userInfo.getOpenid());
        values.setImageUrl(userInfo.getHeadimgurl());

        values.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(VX api) {
        return null;
    }

    @Override
    public void updateStatus(VX api, String message) {

    }
}

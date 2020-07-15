package com.tianling.springsecurity.social.wx.api;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/29 22:04
 */
public interface VX {
    VXUserInfo getUserInfo(String openId);
}

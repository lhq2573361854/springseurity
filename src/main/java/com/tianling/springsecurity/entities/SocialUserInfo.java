package com.tianling.springsecurity.entities;

import lombok.Data;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/29 15:01
 */
@Data
public class SocialUserInfo {
    private String providerId;
    private String providerUserId;
    private String nickname;
    private String heading;
}

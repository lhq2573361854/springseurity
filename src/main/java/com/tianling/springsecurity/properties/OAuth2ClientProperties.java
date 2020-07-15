package com.tianling.springsecurity.properties;

import lombok.Data;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/4 2:29
 */
@Data
public class OAuth2ClientProperties {
    /**
     * 第三方应用appId
     */
    private String clientId;
    /**
     * 第三方应用appSecret
     */
    private String clientSecret;
    /**
     * 针对此应用发出的token的有效时间
     */
    private int accessTokenValidateSeconds = 7200;
}

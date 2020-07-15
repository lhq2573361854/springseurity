package com.tianling.springsecurity.properties;

import lombok.Data;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/4 2:30
 */
@Data
public class OAuth2Properties {

    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "tian";
    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

}

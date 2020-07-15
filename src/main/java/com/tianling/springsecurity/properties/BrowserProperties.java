package com.tianling.springsecurity.properties;

import lombok.Data;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/21 22:50
 */
@Data
public class BrowserProperties {
    private String loginPage ="/authentication/login";
    private int expire = 3600;
    private String signUpUrl = "/authentication/register";
    private String signOut = "/signOut";
    private String out = "/authentication/out";
    private SessionProperties session = new SessionProperties();
}

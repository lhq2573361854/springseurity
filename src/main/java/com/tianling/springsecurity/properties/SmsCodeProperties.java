package com.tianling.springsecurity.properties;

import lombok.Data;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/23 9:52
 */
@Data
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;
    private String url;
}

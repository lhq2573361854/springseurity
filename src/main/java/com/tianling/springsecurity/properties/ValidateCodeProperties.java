package com.tianling.springsecurity.properties;

import lombok.Data;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/23 9:52
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
    private SmsCodeProperties sms = new SmsCodeProperties();
}

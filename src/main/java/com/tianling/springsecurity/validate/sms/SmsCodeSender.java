package com.tianling.springsecurity.validate.sms;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/23 22:11
 */
public interface SmsCodeSender {
    void send(String mobile,String code);
}

package com.tianling.springsecurity.validate.sms;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/23 22:14
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向设备 " + mobile + "发送了一验证码，验证码是:" + code);
    }
}

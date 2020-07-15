package com.tianling.springsecurity.config.common;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/29 16:58
 */
@Component
public class MyConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        String displayName = connection.getDisplayName();
        return null;
    }
}

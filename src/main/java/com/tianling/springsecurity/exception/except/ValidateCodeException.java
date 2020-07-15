package com.tianling.springsecurity.exception.except;


import org.springframework.security.core.AuthenticationException;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/22 20:08
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}

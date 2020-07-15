package com.tianling.springsecurity.config.common;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/4 13:36
 */
public class AppSecretException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -1629364510827838114L;

    public AppSecretException(String msg){
        super(msg);
    }

}

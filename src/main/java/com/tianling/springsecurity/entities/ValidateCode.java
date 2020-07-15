package com.tianling.springsecurity.entities;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/22 18:40
 */
@Data
public class ValidateCode implements Serializable {

    private String code;
    private LocalDateTime expireTime;
    private String url;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}

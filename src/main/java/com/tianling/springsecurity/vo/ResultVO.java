package com.tianling.springsecurity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/1 16:45
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -2512067269292159149L;

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}

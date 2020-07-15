package com.tianling.springsecurity.exception.handler;

import com.tianling.springsecurity.exception.except.LoginRunTimeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author TianLing
 * Date 2020/6/4 15:56
 * Description
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(LoginRunTimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String loginRunTimeExceptionHandler(){
        return "this is  a simple ";
    }
}

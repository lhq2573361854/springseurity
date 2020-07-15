package com.tianling.springsecurity.controller;

import com.tianling.springsecurity.enums.SecurityConstants;
import com.tianling.springsecurity.validate.ValidateCodeProcessorHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/22 18:51
 */
@Controller
@Slf4j
public class ValidateController {
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";


//    @Autowired
//    ValidateService validateService;

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;
    /**
     * 图片验证码的生成
     * @param request
     * @param response
     */
//    @GetMapping("/code/image")
//    public void imageCode(HttpServletRequest request, HttpServletResponse response){
//        validateService.setImageCodeValues(request);
//        validateService.imageCodeGenerator(request,response);
//    }

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
            throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }

    /**
     * 短信验证码的生成
     * @param request
     * @param response
     */
//    @GetMapping("/code/sms")
//    public void smsCode(HttpServletRequest request, HttpServletResponse response){
//        validateService.smsCodeGenerator(request,response);
//    }




}

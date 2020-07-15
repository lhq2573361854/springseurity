package com.tianling.springsecurity.controller;

import cn.hutool.core.util.StrUtil;
import com.tianling.springsecurity.entities.SimpleResponse;
import com.tianling.springsecurity.entities.SocialUserInfo;
import com.tianling.springsecurity.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/21 23:05
 */
@Controller
@Slf4j
@RequestMapping("authentication")
public class LoginController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    SecurityProperties securityProperties;

    @RequestMapping("require")
    @ResponseBody
    public SimpleResponse Require(HttpServletRequest request, HttpServletResponse response){
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info(redirectUrl);
            if (StrUtil.endWithIgnoreCase(redirectUrl, ".html")){
                try {
                    redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new SimpleResponse("需要身份认证");
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/out")
    public String out(){
        return "out";
    }
    @GetMapping("/register")
    public String  register(){
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public void  register(String username,HttpServletRequest request){
        providerSignInUtils.doPostSignUp(username,new ServletWebRequest(request));
    }

    @GetMapping("/social/user")
    @ResponseBody
    public SocialUserInfo userInfo(HttpServletRequest request){
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        System.out.println("providerSignInUtils = " + providerSignInUtils);
        Connection<?> connectionFromSession = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        System.out.println("connectionFromSession = " + connectionFromSession);
        socialUserInfo.setProviderId(connectionFromSession.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connectionFromSession.getKey().getProviderUserId());
        socialUserInfo.setNickname(connectionFromSession.getDisplayName());
        socialUserInfo.setHeading(connectionFromSession.getImageUrl());

        return socialUserInfo;
    }

    @GetMapping("/binding")
    public String  binding(){
        return "binding";
    }


}

package com.tianling.springsecurity.authentication;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianling.springsecurity.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/1 19:40
 */
@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private ObjectMapper objectMapper = new ObjectMapper();
    private String logOutUrl;

    public MyLogoutSuccessHandler(String logOutUrl) {
        this.logOutUrl = logOutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("退出成功");
        if (StrUtil.equalsIgnoreCase("/",logOutUrl)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ResultVOUtil.success("退出成功")));
        }else {
            response.sendRedirect(logOutUrl);
        }
    }
}

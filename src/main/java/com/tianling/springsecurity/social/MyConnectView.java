package com.tianling.springsecurity.social;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/30 11:30
 */
@Slf4j
public class MyConnectView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        log.info(map.toString());
        if (map.get("connections") == null) {

            httpServletResponse.getWriter().write("<h3>解绑成功</h3>");
        } else {
            httpServletResponse.getWriter().write("<h3>绑定成功</h3>");
        }
    }
}

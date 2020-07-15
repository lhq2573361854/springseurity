package com.tianling.springsecurity.social.wx.api;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/29 22:41
 */
@Slf4j
public class VXImpl extends AbstractOAuth2ApiBinding implements VX {
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";
    private ObjectMapper objectMapper = new ObjectMapper();

    public VXImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }


    @Override
    public VXUserInfo getUserInfo(String openId) {
        String url = URL_GET_USER_INFO + openId;
        String response = getRestTemplate().getForObject(url, String.class);
        if(StrUtil.contains(response, "errcode")) {
            return null;
        }
        VXUserInfo vXUserInfo = null;
        try {
            vXUserInfo = objectMapper.readValue(response, VXUserInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info(vXUserInfo.toString());
        return vXUserInfo;
    }
}

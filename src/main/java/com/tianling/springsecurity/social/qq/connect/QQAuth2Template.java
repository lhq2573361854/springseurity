package com.tianling.springsecurity.social.qq.connect;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/28 23:43
 */
@Slf4j
public class QQAuth2Template extends OAuth2Template {
    public QQAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String s = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        log.info("获取accessToke的响应：" + s);
        String[] split = StrUtil.split(s, "&");
        String accessToken = StrUtil.subAfter(split[0], "=",true);
        log.info("accessToken = " + accessToken);
        Long expireIn = Long.valueOf(StrUtil.subAfter(split[1], "=", true));
        log.info("expireIn = " + expireIn);
        String refreshToken = StrUtil.subAfter(split[2], "=",true);
        log.info("refreshToken = " + refreshToken);
        return new AccessGrant(accessToken,null,refreshToken,expireIn);
    }
}

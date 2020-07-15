package com.tianling.springsecurity.social.qq.api;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/27 21:34
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    private String appId;
    private String openId;
    public static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    public static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    private ObjectMapper mapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url,String.class);
        log.info("result = " + result.toString());
        String s = StrUtil.subBetween(result, "\"openid\":\"", "\"}");
        log.info("openId = " + s);
        this.openId = s;
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USER_INFO,appId,openId);
        String result = getRestTemplate().getForObject(url, String.class);
        QQUserInfo o = null;
        try {
            o = mapper.readValue(result, QQUserInfo.class);
            o.setOpenId(openId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return o;
    }


}

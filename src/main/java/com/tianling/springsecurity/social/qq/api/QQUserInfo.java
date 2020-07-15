package com.tianling.springsecurity.social.qq.api;

import lombok.Data;
import lombok.ToString;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/27 21:33
 */
@Data
@ToString
public class QQUserInfo {
    private String ret;
    private String msg;
    private String nickname;
    private String gender;
    private String figureurl;
    private String figureurl_qq;
    private String figureurl_1;
    private String figureurl_2;
    private String figureurl_qq_1;
    private String figureurl_qq_2;
    private String gender_type;
    private String is_yellow_vip;
    private String vip;
    private String yellow_vip_level;
    private String level;
    private String is_yellow_year_vip;
    private String openId;
    private String is_lost;
    private String province;
    private String city;
    private String year;
    private String constellation;
    private String figureurl_type;
}

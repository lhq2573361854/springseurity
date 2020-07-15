package com.tianling.springsecurity.properties;

import lombok.Data;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/30 0:30
 */
@Data
public class VXProperties extends SocialProperties {
    private String providerId = "weixin";
}

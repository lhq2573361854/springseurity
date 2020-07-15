package com.tianling.springsecurity.properties;

import lombok.Data;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/28 0:31
 */
@Data
public class AllSocialProperties {
    private QQProperties qq = new QQProperties();
    private VXProperties vx = new VXProperties();
    private String filterProcessesUrl;
}

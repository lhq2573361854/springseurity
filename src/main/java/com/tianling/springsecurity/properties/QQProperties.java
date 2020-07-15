package com.tianling.springsecurity.properties;

import lombok.Data;
import lombok.ToString;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/28 0:06
 */
@Data
@ToString
public class QQProperties extends SocialProperties {
   private String providerId = "qq";
}

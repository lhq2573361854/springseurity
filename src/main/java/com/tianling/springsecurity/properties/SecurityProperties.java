package com.tianling.springsecurity.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/21 22:51
 */
@ConfigurationProperties(prefix = "my.security")
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private AllSocialProperties social = new AllSocialProperties();
    private OAuth2Properties oauth2 = new OAuth2Properties();
}

package com.tianling.springsecurity.config.common;

import com.tianling.springsecurity.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/26 15:32
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}

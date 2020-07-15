package com.tianling.springsecurity.properties;

import lombok.Data;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/23 9:49
 */
@Data
public class ImageCodeProperties {
    private int width = 100;
    private int height = 40;
    private int length = 6;
    private int expireIn = 60;
    private String urls;
    private String imageType = "JPEG";
}

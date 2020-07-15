package com.tianling.springsecurity.entities;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * author: TianLing
 * Year: 2020
 * DateTime: 2020/6/22 18:40
 */
@Data
public class ImageCode extends ValidateCode {

    private BufferedImage bufferedImage;
    private String imageType;
    public ImageCode(BufferedImage bufferedImage, String code, int expireIn) {
        super(code,expireIn);
        this.bufferedImage = bufferedImage;
    }

    public ImageCode(BufferedImage bufferedImage, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.bufferedImage = bufferedImage;
    }

}

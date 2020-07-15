package com.tianling.springsecurity.validate.image;

import com.tianling.springsecurity.entities.ImageCode;
import com.tianling.springsecurity.validate.Impl.AppAbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/6/24 10:19
 */
@Component
public class ImageValidateCodeProcessor extends AppAbstractValidateCodeProcessor<ImageCode> {


    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        validateCodeGeneratorImpl.output(validateCode.getBufferedImage(),request.getResponse().getOutputStream());
    }

    public ImageValidateCodeProcessor() {
    }
}

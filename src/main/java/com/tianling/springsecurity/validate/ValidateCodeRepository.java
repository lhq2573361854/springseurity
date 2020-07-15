package com.tianling.springsecurity.validate;

import com.tianling.springsecurity.entities.ValidateCode;
import com.tianling.springsecurity.enums.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/2 17:49
 */
public interface ValidateCodeRepository {
    /**
     * 保存验证码
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);
    /**
     * 获取验证码
     * @param request
     * @param validateCodeType
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);
    /**
     * 移除验证码
     * @param request
     * @param codeType
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);
}

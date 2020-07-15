package com.tianling.springsecurity.validate;

import com.tianling.springsecurity.entities.ValidateCode;
import com.tianling.springsecurity.enums.ValidateCodeType;
import com.tianling.springsecurity.exception.except.ValidateCodeException;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author: TianLing
 * @Year: 2020
 * @DateTime: 2020/7/2 17:50
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        redisTemplate.opsForValue().set(buildKey(request, type), code, 30, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, type));
        if (value == null) {
            return null;
        }
        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        redisTemplate.delete(buildKey(request, type));
    }

    /**
     * 构建验证码在redis中的key ---- 该key类似与cookie
     * @param request
     * @param type
     * @return
     */
    private String buildKey(ServletWebRequest request, ValidateCodeType type) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求头中携带deviceId参数");

        }
        return "code:" + type.toString().toLowerCase() + ":" + deviceId;
    }
}

package com.example.demo.common.exception;

import com.example.demo.common.codeEnum.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 杨小平
 */
@RestControllerAdvice
public class ThrowException {

    private static final Logger logger = LoggerFactory.getLogger(ThrowException.class);

    private static final String CODE = "400";

    @ExceptionHandler(Exception.class)
    public Map exceptionHandler(Exception e) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", CODE);
        map.put("msg", e.getMessage());
        map.put("data", null);
        map.put("success", false);
        map.put("failed", true);
        return map;
    }

    @ExceptionHandler(MyException.class)
    public Map exceptionHandler(MyException e) {
        Map<String, Object> map = new LinkedHashMap<>();
        ErrorCodeEnum errorCodeEnum = e.getErrorCodeEnum();
        map.put("code", errorCodeEnum.getCode());
        map.put("msg", errorCodeEnum.getMsg());
        map.put("data", null);
        map.put("success", false);
        map.put("failed", true);
        return map;
    }
}

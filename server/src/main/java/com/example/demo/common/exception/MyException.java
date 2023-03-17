package com.example.demo.common.exception;

import com.example.demo.common.codeEnum.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 错误报错返回
 * @author 杨小平
 */
@ControllerAdvice
public class MyException extends YxpException{

    private static final Logger log = LoggerFactory.getLogger(MyException.class);

    public MyException(ErrorCodeEnum errorCodeEnum, Throwable cause) {
        super(errorCodeEnum);
        log.error(errorCodeEnum.getMsg(), cause);
    }

    @Deprecated
    public MyException(String message) {
        super(message);
        log.error(message);
    }

    public MyException() {
    }

    public MyException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
        log.error(errorCodeEnum.getMsg());
    }
    @Deprecated
    public MyException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public MyException(ErrorCodeEnum errorCodeEnum, String msg, Object... para) {
        super(errorCodeEnum, msg, para);
    }

    @Deprecated
    public MyException(String format, Object... args) {
        super(String.format(format, args));
    }

    public MyException(ErrorCodeEnum errorCodeEnum, String msg) {
        super(errorCodeEnum, msg);
        log.error(errorCodeEnum.getMsg() + ":" + msg);
    }
}

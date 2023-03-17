package com.example.demo.common.exception;

import com.example.demo.common.codeEnum.ErrorCodeEnum;

/**
 * @author 杨小平
 */
public class YxpException extends RuntimeException{
    private ErrorCodeEnum errorCodeEnum;

    @Deprecated
    public YxpException() {
    }

    @Deprecated
    public YxpException(String msg) {
        super(msg);
    }

    @Deprecated
    public YxpException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * 不建议长期使用
     * 可以在错误描述比较简单的场景
     * 但是前端不太好根据错误码判断异常场景
     * @param format
     * @param args
     */
    @Deprecated
    public YxpException(String format, Object... args) {

        super(String.format(format, args));
    }

    /**
     *适用于只抛出错误码的场景
     * 不建议一直这么用，简单的判断逻辑的场景可以，不用抛出详细信息的那种地方
     * @param errorCodeEnum
     */
    public YxpException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.errorCodeEnum = errorCodeEnum;
    }

    /**
     * 适用于 错误码中有多个占位符的场景
     * @param errorCodeEnum
     * @param para
     */
    public YxpException(ErrorCodeEnum errorCodeEnum, Object... para) {
        super(String.format(errorCodeEnum.getMsg(), para));
        this.errorCodeEnum = errorCodeEnum.setZhMsg(String.format(errorCodeEnum.getMsg(), para));
    }

    /**
     * 适用于 只使用错误码，错误描述要被覆盖掉的场景
     * 覆盖时，msg要写错误原因+占位符，para里面根据占位符去拼错误描述
     * @param errorCodeEnum
     * @param msg
     * @param para
     */
    public YxpException(ErrorCodeEnum errorCodeEnum, String msg, Object... para) {
        super(String.format(msg, para));
        String format = String.format(msg, para);
        this.errorCodeEnum = errorCodeEnum.setZhMsg(format);
    }

    /**
     * 适用于，错误码中定义了错误信息占位符，msg里面是错误描述的场景
     * 错误码中只有一个占位符
     * @param errorCodeEnum
     * @param msg
     */
    public YxpException(ErrorCodeEnum errorCodeEnum, String msg) {
        super(String.format(errorCodeEnum.getMsg(), msg));
        this.errorCodeEnum = errorCodeEnum.setZhMsg(String.format(errorCodeEnum.getMsg(), msg));
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }
}

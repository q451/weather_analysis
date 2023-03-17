package com.example.demo.common.response;

import com.example.demo.common.codeEnum.ErrorCodeEnum;
import com.example.demo.common.codeEnum.Status;

/**
 * @author 杨小平
 */
public class ResultUtil {
    public static Result result(ErrorCodeEnum errorCodeEnum) {
        return new Result(errorCodeEnum.getCode(), errorCodeEnum.getMsg());
    }

    public static <T> Result result(ErrorCodeEnum errorCodeEnum, T data) {
        Result result = new Result<>(errorCodeEnum.getCode(), errorCodeEnum.getMsg());
        result.setData(data);
        return result;
    }

    public static Result success() {
        return new Result(Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
    }

    public static <T> Result success(String reason) {
        Result result = success();
        result.setData(reason);
        return result;
    }

    public static <T> Result success(T data) {
        Result result = success();
        result.setData(data);
        return result;
    }

    public static <T> Result success(String reason, T data) {
        Result result = new Result(Status.SUCCESS.getCode(), reason);
        result.setData(data);
        return result;
    }

    public static <T> Result error(ErrorCodeEnum errorCodeEnum, T data) {
        Result result = error(errorCodeEnum);
        result.setData(data);
        return result;
    }

    public static <T> Result error(ErrorCodeEnum errorCodeEnum) {
        return new Result(errorCodeEnum.getCode(), errorCodeEnum.getMsg());
    }
}

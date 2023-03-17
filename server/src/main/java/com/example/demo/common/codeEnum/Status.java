package com.example.demo.common.codeEnum;

import java.util.Optional;

/**
 * Status
 * 返回状态编码
 *
 * @author 杨小平
 */

public enum Status {

    /**
     * 成功状态码
     */
    SUCCESS(0, "success", "成功"),

    INTERNAL_SERVER_ERROR_ARGS(10000, "Internal Server Error: {0}", "服务端异常: {0}"),

    REQUEST_PARAMS_NOT_VALID_ERROR(10001, "request parameter {0} is not valid", "请求参数[{0}]无效"),

    TASK_TIMEOUT_PARAMS_ERROR(10002, "task timeout parameter is not valid", "任务超时参数无效"),

    USER_NAME_EXIST(10003, "user name already exists", "用户名已存在"),

    USER_NAME_NULL(10004, "user name is null", "用户名不能为空"),

    HDFS_OPERATION_ERROR(10006, "hdfs operation error", "hdfs操作错误"),

    USER_NOT_EXIST(10010, "user {0} not exists", "用户[{0}]不存在"),

    LOGIN_SUCCESS(10042, "login success", "登录成功"),

    USER_LOGIN_FAILURE(10043, "user login failure", "用户登录失败"),

    FILE_NOT_NULL(20008, "upload resource file size exceeds limit", "文件不能为空"),

    FILE_DATA_NOT_NULL(20009, "file data not sure", "文件内容不能为空"),

    FILE_TEMPLATE_NOT_SURE(20010, "file template  not sure", "请传入正确格式的文件"),

    IMPORT_FILE_FAIL(150001, "import file fail", "导入文件失败"),

    EXPORT_FILE_FAIL(150002, "export file fail", "导出文件失败"),

    RESOURCE_SIZE_EXCEED_LIMIT(150006, "upload resource file size exceeds limit", "上传资源文件大小超过限制"),

    RESOURCE_SUFFIX_ERROR(150005, "upload resource file size exceeds limit", "导入文件只支持xlsx,xls");

    private final int code;
    private final String enMsg;
    private final String zhMsg;

    Status(int code, String enMsg, String zhMsg) {
        this.code = code;
        this.enMsg = enMsg;
        this.zhMsg = zhMsg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.zhMsg;
    }

    /**
     * Retrieve Status enum entity by status code.
     *
     * @param code
     * @return
     */
    public static Optional<Status> findStatusBy(int code) {
        for (Status status : Status.values()) {
            if (code == status.getCode()) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}

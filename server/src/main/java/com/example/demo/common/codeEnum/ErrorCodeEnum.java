package com.example.demo.common.codeEnum;
/**
 * ErrorCodeEnum
 *
 * 错误码定义
 * @author 杨小平
 */
public enum ErrorCodeEnum {

    /**
     * 成功状态码
     */
    SUCCESS(0, "success", "成功"),
    /**
     * 1
     */
    TEST(1001, "test", "测试"),

    TEST_ERROR(1002, "test error", "测试失败"),

    TWO_PASSWORD_NOT_SAME(1004, "two inputs password not same", "两次输入密码不一致"),

    DELETE_USER_FAIL(1005, "delete failed", "删除用户失败，失败原因：%s"),

    INSERT_USER_FAIL(1006, "insert failed", "新增用户失败，失败原因：%s"),

    UPDATE_USER_FAIL(1007, "update failed", "修改用户失败，失败原因：%s"),

    SELECT_USER_FAIL(1008, "select failed", "查询用户失败，失败原因：%s"),

    USER_EXIST(1009, "user exist", "用户已存在"),

    USER_NOT_EXIST(1010, "user not exist", "用户不存在"),

    PASSWORD_ERROR(1011, "password error", "密码错误"),

    EMAIL_CODE_ERROR(1012, "email code error", "邮箱验证码输入错误"),

    DATABASE_TABLE_NOT_EXIST(1017, "DATABASE_TABLE_NOT_EXIST", "数据库表不存在"),

    DATABASE_CONNECTION_FAIL(1018, "DATABASE_CONNECTION_FAIL", "数据库连接异常"),

    DATABASE_FIELD_NOT_EXIST(1019, "DATABASE_FIELD_NOT_EXIST", "数据库字段不存在"),

    ENABLE_FAILED(1020, "ENABLE_FAILED", "启用失败"),

    DEACTIVATION_FAILED(1021, "DEACTIVATION_FAILED", "停用失败"),

    EXPORT_FILE_FAIL(1022, "export file fail", "导出文件失败"),

    IMPORT_FILE_FAIL_REASON(1023, "import file fail", "导入文件失败,原因：%s"),

    CHECK_URL_ERROR(1024, " check url error", "请检查url合法性"),

    FILE_NOT_NULL(20008, "upload resource file size exceeds limit", "文件不能为空"),

    RESOURCE_SUFFIX_ERROR(150005, "upload resource file size exceeds limit", "导入文件只支持xlsx,xls"),

    EMAIL_CODE(4000, "email sent fail", "邮箱验证码发送失败"),

    TOKEN_TIME_OUT(9999999,"token is past due","token 已经过期，请重新登陆");

    /**
     * 错误码
     */
    private final int code;
    /**
     * 英文描述
     */
    private String enMsg;
    /**
     * 中文描述
     */
    private String zhMsg;

    ErrorCodeEnum(int code, String enMsg, String zhMsg) {
        this.code = code;
        this.enMsg = enMsg;
        this.zhMsg = zhMsg;
    }

    public int getCode() {
        return this.code;
    }

    public ErrorCodeEnum setZhMsg(String zhMsg) {
        this.zhMsg = zhMsg;
        return this;
    }

    public String getMsg() {
        return this.zhMsg;
    }
}

package com.opendev.enums;

public enum ResultStatusCode {

    SUCCESS(200, "操作成功"),
    ERROR(400, "操作失败！"),
    SIGN_ERROR(120, "签名错误"),
    TIME_OUT(130, "访问超时"),
    BAD_REQUEST(400, "参数解析失败"),
    INVALID_TOKEN(401, "无效的授权码"),
    INVALID_CLIENTID(402, "无效的密钥"),
    NOT_FOUND(404, "资源不存在！"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    SYSTEM_ERR(500, "服务器运行异常"),
    NOT_EXIST_USER_OR_ERROR_PWD(10000, "该用户不存在或密码错误"),
    LOGINED_IN(10001, "该用户已登录"),
    NOT_EXIST_BUSINESS(10002, "该商家不存在"),
    SHIRO_ERROR(10003, "登录异常"),
    UNAUTHO_ERROR(10004, "您没有该权限"),
    BIND_PHONE(10010, "请绑定手机号"),
    UPLOAD_ERROR(20000, "上传文件异常"),
    INVALID_CAPTCHA(30005, "无效的验证码"),
    USER_FROZEN(40000, "该用户已被冻结"),

    Add_Entity_Error(500,"实体数据新增异常"),
    Param_Not_Exist(400, "上传参数存在空值"),
    Param_ID_Not_Exist(401,"ID字段存在空值"),
    Field_Value_Invalid(402, "字段取值异常"),
    NOT_FORBIDDEN(403, "很抱歉！您没有访问权限！"),
    DATA_IS_NULL(1005,"数据为空，请填写数据");



    private Integer code;
    private String message;

    ResultStatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

package com.opendev.enums;

public enum ResultStatusCode {

    SUCCESS(200, "操作成功"),
    ERROR(400, "操作失败！"),
    BAD_REQUEST(400, "参数解析失败"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    UNAUTHO_ERROR(10004, "您未登录，请登录！"),
    INVALID_TOKEN(401, "无效的授权码"),
    SYSTEM_ERR(500, "服务器运行异常"),
    Resource_Not_Exist(404, "请求资源不存在，可能被删除或参数错误"),
    Add_Entity_Error(500,"实体数据新增异常"),
    Param_Not_Exist(400, "上传参数存在空值"),
    Param_ID_Not_Exist(401,"ID字段存在空值"),
    Field_Value_Invalid(402, "字段取值异常"),
    NOT_FORBIDDEN(403, "很抱歉！您没有访问权限！"),
    DATA_IS_NULL(1005,"数据为空，请填写数据"),
    NOT_FOUND(404, "资源不存在！");


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

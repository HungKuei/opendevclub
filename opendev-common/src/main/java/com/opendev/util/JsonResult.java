package com.opendev.util;


import com.opendev.base.APIResponse;
import com.opendev.enums.ResultStatusCode;


/**
 *  Json 返回结果对象
 * @author hungkuei
 * @date 2019-08-02
 */
public class JsonResult {

    /**
     * 简单成功返回结果
     * @return
     */
    public static APIResponse success(){
        return init(ResultStatusCode.SUCCESS.getCode(), ResultStatusCode.SUCCESS.getMessage());
    }
    /**
     * 自定义成功返回结果和状态码
     * @param code
     * @param msg
     * @param result
     * @return
     */
    public static APIResponse<Object> success(Integer code, String msg, Object result){
        return init(code, msg, result);
    }

    /**
     * 成功返回结果和状态码
     * @param resultStatusCode
     * @param result
     * @return
     */
    public static APIResponse<Object> success(ResultStatusCode resultStatusCode, Object result){
        return init(resultStatusCode, result);
    }

    /**
     * 简单失败返回结果
     * @return
     */
    public static APIResponse error(){
        return init(ResultStatusCode.ERROR.getCode(), ResultStatusCode.ERROR.getMessage());
    }

    /**
     * 自定义失败返回结果和状态码
     * @param code
     * @param msg
     * @param result
     * @return
     */
    public static APIResponse<Object> error(Integer code, String msg, Object result){
        return init(code, msg, result);
    }

    /**
     * 失败返回结果和状态码
     * @param resultStatusCode
     * @param result
     * @return
     */
    public static APIResponse<Object> error(ResultStatusCode resultStatusCode, Object result){
        return init(resultStatusCode, result);
    }

    /**
     * 初始化模板结果封装
     * @param resultStatusCode
     * @return
     */
    public static APIResponse<Object> init(ResultStatusCode resultStatusCode, Object result){
        return init(resultStatusCode.getCode(), resultStatusCode.getMessage());
    }

    /**
     * 初始化简单封装
     * @param code
     * @param msg
     * @return
     */
    public static APIResponse init(Integer code, String msg){
        return new APIResponse(code, msg);
    }

    /**
     * 返回结果以及状态码
     * @param code
     * @param msg
     * @param result
     * @return
     */
    public static APIResponse init(Integer code, String msg, Object result){
        return new APIResponse(code, msg, result);
    }
}

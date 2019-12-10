package com.opendev.utils;


import com.opendev.base.BaseResponse;
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
    public static BaseResponse success(){
        return init(ResultStatusCode.SUCCESS.getCode(), ResultStatusCode.SUCCESS.getMessage());
    }
    /**
     * 自定义成功返回结果和状态码
     * @param code
     * @param msg
     * @param result
     * @return
     */
    public static BaseResponse success(Integer code, String msg, Object result){
        return init(code, msg, result);
    }

    /**
     * 成功返回结果和状态码
     * @param resultStatusCode
     * @param result
     * @return
     */
    public static BaseResponse success(ResultStatusCode resultStatusCode, Object result){
        return init(resultStatusCode, result);
    }

    /**
     * 简单失败返回结果
     * @return
     */
    public static BaseResponse error(){
        return init(ResultStatusCode.ERROR.getCode(), ResultStatusCode.ERROR.getMessage());
    }

    /**
     * 自定义失败返回结果和状态码
     * @param code
     * @param msg
     * @param result
     * @return
     */
    public static BaseResponse error(Integer code, String msg, Object result){
        return init(code, msg, result);
    }


    public static BaseResponse error(ResultStatusCode resultStatusCode){
        return init(resultStatusCode.getCode(), resultStatusCode.getMessage());
    }
    /**
     * 失败返回结果和状态码
     * @param resultStatusCode
     * @param result
     * @return
     */
    public static BaseResponse error(ResultStatusCode resultStatusCode, Object result){
        return init(resultStatusCode, result);
    }

    /**
     * 初始化模板结果封装
     * @param resultStatusCode
     * @return
     */
    public static BaseResponse init(ResultStatusCode resultStatusCode, Object result){
        return init(resultStatusCode.getCode(), resultStatusCode.getMessage(), result);
    }

    /**
     * 初始化简单封装
     * @param code
     * @param msg
     * @return
     */
    public static BaseResponse init(Integer code, String msg){
        return new BaseResponse(code, msg);
    }

    /**
     * 返回结果以及状态码
     * @param code
     * @param msg
     * @param result
     * @return
     */
    public static BaseResponse init(Integer code, String msg, Object result){
        return new BaseResponse(code, msg, result);
    }
}

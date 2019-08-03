package com.opendev.util;


import com.opendev.base.BaseResponse;
import com.opendev.enums.ResultStatusCode;


/**
 * 返回结果对象
 * @author hungkuei
 * @date 2019-08-02
 */
public class JsonResult {

    /**
     * 统一成功响应
     * @return
     */
    public static BaseResponse<Object> success(){
        return init(ResultStatusCode.SUCCESS);
    }

    /**
     * 自定义成功响应信息
     * @param msg
     * @return
     */
    public static BaseResponse<Object> success(String msg){
        return init(ResultStatusCode.SUCCESS.getCode(), msg);
    }

    /**
     * 统一失败响应
     * @return
     */
    public static BaseResponse<Object> error(){
        return init(ResultStatusCode.ERROR);
    }

    /**
     * 自定义失败响应信息
     * @param msg
     * @return
     */
    public static BaseResponse<Object> error(String msg){
        return init(ResultStatusCode.ERROR.getCode(), msg);
    }

    /**
     * 初始化模板结果封装
     * @param resultStatusCode
     * @return
     */
    public static BaseResponse<Object> init(ResultStatusCode resultStatusCode){
        return init(resultStatusCode.getCode(), resultStatusCode.getMessage());
    }

    /**
     * 初始化简单封装
     * @param code
     * @param msg
     * @return
     */
    public static BaseResponse<Object> init(Integer code, String msg){
        return new BaseResponse(code, msg);
    }
}

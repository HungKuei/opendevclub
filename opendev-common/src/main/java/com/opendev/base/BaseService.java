package com.opendev.base;

import com.opendev.enums.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseService {

    @Autowired
    protected BaseRedisService baseRedisService;
    /**
     * 简单成功返回结果
     * @return
     */
    public BaseResponse success(){
        return init(ResultStatusCode.SUCCESS.getCode(), ResultStatusCode.SUCCESS.getMessage());
    }

    /**
     * 自定义返回内容
     * @return
     */
    public BaseResponse success(String msg){
        return init(ResultStatusCode.SUCCESS.getCode(), msg, null);
    }

    /**
     * 返回数据
     * @param data
     * @return
     */
    public BaseResponse success(Object data){
        return init(ResultStatusCode.SUCCESS.getCode(), ResultStatusCode.SUCCESS.getMessage(), data);
    }
    /**
     * 自定义成功返回结果和状态码
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public BaseResponse success(Integer code, String msg, Object data){
        return init(code, msg, data);
    }

    /**
     * 成功返回结果和状态码
     * @param resultStatusCode
     * @param result
     * @return
     */
    public BaseResponse success(ResultStatusCode resultStatusCode, Object result){
        return init(resultStatusCode, result);
    }

    /**
     * 简单失败返回结果
     * @return
     */
    public BaseResponse error(){
        return init(ResultStatusCode.ERROR.getCode(), ResultStatusCode.ERROR.getMessage());
    }

    /**
     * 自定义响应信息
     * @return
     */
    public BaseResponse error(String msg){
        return init(ResultStatusCode.ERROR.getCode(), msg);
    }

    /**
     * 自定义失败返回结果和状态码
     * @param code
     * @param msg
     * @param result
     * @return
     */
    public BaseResponse<Object> error(Integer code, String msg, Object result){
        return init(code, msg, result);
    }


    public BaseResponse<Object> error(ResultStatusCode resultStatusCode){
        return init(resultStatusCode.getCode(), resultStatusCode.getMessage());
    }
    /**
     * 失败返回结果和状态码
     * @param resultStatusCode
     * @param result
     * @return
     */
    public BaseResponse<Object> error(ResultStatusCode resultStatusCode, Object result){
        return init(resultStatusCode, result);
    }

    /**
     * 初始化模板结果封装
     * @param resultStatusCode
     * @return
     */
    public BaseResponse<Object> init(ResultStatusCode resultStatusCode, Object result){
        return init(resultStatusCode.getCode(), resultStatusCode.getMessage(), result);
    }

    /**
     * 初始化简单封装
     * @param code
     * @param msg
     * @return
     */
    public BaseResponse init(Integer code, String msg){
        return new BaseResponse(code, msg);
    }

    /**
     * 返回结果以及状态码
     * @param code
     * @param msg
     * @param result
     * @return
     */
    public BaseResponse init(Integer code, String msg, Object result){
        return new BaseResponse(code, msg, result);
    }
}

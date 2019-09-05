package com.opendev.base;

import com.opendev.enums.ResultStatusCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * API 统一返回相应参数实体类
 * @author hungkuei
 * @param <T>
 */

@Data
public class APIResponse<T> {
    private Integer code;
    private String msg;
    private T result;

    public APIResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public APIResponse(Integer code, String msg, T result){
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public APIResponse(ResultStatusCode resultStatusCode){
        this(resultStatusCode.getCode(),resultStatusCode.getMessage());
    }

    public APIResponse(HttpStatus httpStatus){
        this(httpStatus.value(), httpStatus.getReasonPhrase());
    }
}

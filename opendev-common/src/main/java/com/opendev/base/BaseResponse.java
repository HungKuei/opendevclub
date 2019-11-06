package com.opendev.base;

import com.opendev.enums.ResultStatusCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public BaseResponse(){
    }

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public BaseResponse(ResultStatusCode resultStatusCode){
        this(resultStatusCode.getCode(),resultStatusCode.getMessage());
    }

    public BaseResponse(HttpStatus httpStatus){
        this(httpStatus.value(), httpStatus.getReasonPhrase());
    }
}

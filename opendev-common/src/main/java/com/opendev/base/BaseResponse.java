package com.opendev.base;

import com.opendev.enums.ResultStatusCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 171791176757477130L;

    private Integer code;
    private String msg;
    private T data;
    private Long count;

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

    public BaseResponse(Integer code, String msg, T data, Long count){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }
    public BaseResponse(ResultStatusCode resultStatusCode){
        this(resultStatusCode.getCode(),resultStatusCode.getMessage());
    }

    public BaseResponse(HttpStatus httpStatus){
        this(httpStatus.value(), httpStatus.getReasonPhrase());
    }
}

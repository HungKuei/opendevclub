package com.opendev.base;

import com.opendev.enums.ResultStatusCode;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

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

    public APIResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        response.setCharacterEncoding("UTF-8");
    }

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
}

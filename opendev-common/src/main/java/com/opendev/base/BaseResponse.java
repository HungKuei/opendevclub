package com.opendev.base;

import com.opendev.enums.ResultStatusCode;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 统一返回相应参数实体类
 * @param <T>
 */

@Data
public class BaseResponse<T> extends HashMap<String, Object> {
    private Integer code;
    private String msg;

    public BaseResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        response.setCharacterEncoding("UTF-8");
    }

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(ResultStatusCode resultStatusCode){
        this(resultStatusCode.getCode(),resultStatusCode.getMessage());
    }

    @Override
    public BaseResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

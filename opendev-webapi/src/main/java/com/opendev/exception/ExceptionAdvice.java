package com.opendev.exception;

import com.opendev.base.APIResponse;
import com.opendev.enums.ResultStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, BindException.class,
            ServletRequestBindingException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public APIResponse handleHttpMessageNotReadableException(Exception e) {
        log.error("参数解析失败:"+ e.getMessage());
        if (e instanceof BindException){
            return new APIResponse(ResultStatusCode.BAD_REQUEST.getCode(), ((BindException)e).getAllErrors().get(0).getDefaultMessage());
        }
        return new APIResponse(ResultStatusCode.BAD_REQUEST.getCode(), e.getMessage());
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public APIResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法:"+ e.getMessage());
        return new APIResponse(ResultStatusCode.METHOD_NOT_ALLOWED.getCode(), e.getMessage());
    }

    /**
     * shiro权限异常处理
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public APIResponse unauthorizedException(UnauthorizedException e){
        log.error(e.getMessage());
        return new APIResponse(ResultStatusCode.UNAUTHO_ERROR);
    }

    /**
     * 500
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public APIResponse handleException(Exception e) {
        //e.printStackTrace();
        log.error("服务运行异常"+ e.getMessage());
        return new APIResponse(ResultStatusCode.SYSTEM_ERR);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public APIResponse httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        //e.printStackTrace();
        log.error("HttpMedia类型不支持异常:" + e.getMessage());
        return new APIResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), e.getMessage());
    }
}
package com.opendev.util;

import com.opendev.enums.ResultStatusCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResult<T> {

    private int code;

    private String msg;

    private long countPage;

    private List<T> result;

    public PageResult() {

    }
    public PageResult(ResultStatusCode resultStatusCode, List<T> result) {
        this.code = resultStatusCode.getCode();
        this.msg = resultStatusCode.getMessage();
        this.result = result;
        this.countPage = result.size();
    }
}

package com.opendev.base;

import com.opendev.constant.PublicConstant;
import com.opendev.enums.ResultStatusCode;

public class BaseController{

    // 接口直接返回true 或者false
    public Boolean isSuccess(BaseResponse<?> baseResp) {
        if (baseResp == null) {
            return false;
        }
        if (baseResp.getCode().equals(ResultStatusCode.NOT_FOUND.getCode())) {
            return false;
        }
        if (baseResp.getCode().equals(PublicConstant.HTTP_RES_CODE_500)) {
            return false;
        }
        return true;
    }

}
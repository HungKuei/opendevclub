package com.opendev.utils;

import com.opendev.constant.PublicConstant;

import java.util.UUID;

public class TokenUtil {

    /**
     * 生成会员token
     * @return
     */
    public static String getMemberToken(){
        return PublicConstant.TOKEN + "-" + UUID.randomUUID();
    }

    /**
     * 生成支付token
     * @return
     */
    public static String getPayToken(){
        return PublicConstant.PAY_TOKEN + "-" + UUID.randomUUID();
    }
}

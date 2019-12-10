package com.opendev.mapper;

import com.opendev.bean.PaymentInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

public interface PaymentMapper extends BaseMapper<PaymentInfo> {

    PaymentInfo selectByPayId(@Param("payId") String payId);

    Integer updatePaymentInfo(@Param("paymentInfo") PaymentInfo paymentInfo);
}

package com.ngu.springcloud.service.impl;

import com.ngu.springcloud.entities.CommonResult;
import com.ngu.springcloud.entities.Payment;
import com.ngu.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService
{
    @Override
    public CommonResult<Payment> paymentSQL(Long id)
    {
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}

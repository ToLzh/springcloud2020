package com.ngu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 服务降级，出现故障，则在这里做降级
 */
@Component
public class PaymentFaillbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----paymentInfo_OK  fall back";
    }

    @Override
    public String paymentInfo_error(Integer id) {
        return "-----paymentInfo_error  fall back";
    }
}

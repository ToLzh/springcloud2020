package com.ngu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 若无异常则走服务端
 * 若异常，则访问实现类进行服务降级
 */
@Component
@FeignClient(value="CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFaillbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/error/{id}")
    public String paymentInfo_error(@PathVariable("id") Integer id);
}

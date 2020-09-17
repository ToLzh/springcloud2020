package com.ngu.springcloud.service;

import com.ngu.springcloud.entities.CommonResult;
import com.ngu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE") // 找哪个服务
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    public String getPaymentFeignTimeOut();
}

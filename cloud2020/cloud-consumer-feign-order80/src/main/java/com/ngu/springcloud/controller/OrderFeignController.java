package com.ngu.springcloud.controller;

import com.ngu.springcloud.entities.CommonResult;
import com.ngu.springcloud.entities.Payment;
import com.ngu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/consumer/payment")
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPayment(id);
    }

    @GetMapping(value = "/feign/timeout")
    public String getPaymentFeignTimeOut() {
        return paymentFeignService.getPaymentFeignTimeOut();
    }
}

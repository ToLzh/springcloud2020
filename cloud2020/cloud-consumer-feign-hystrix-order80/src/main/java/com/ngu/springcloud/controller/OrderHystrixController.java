package com.ngu.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ngu.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer/payment")
@DefaultProperties(defaultFallback = "fallback_global")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return  result;
    }

    @GetMapping("hystrix/error/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandle",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    }) // 自定义
//    @HystrixCommand  // 调用通用降级
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        int age = 10 / 0;
        String result = paymentHystrixService.paymentInfo_error(id);
        return  result;
    }

    /**
     * 降级
     * @param id
     * @return
     */
    private String paymentInfo_timeoutHandle(Integer id){
        return  "线程池:"+Thread.currentThread().getName()+"  系统繁忙，请稍后再试,id:  "+id+"\t <・)))><<";
    }

    /**
     * 通用全局降级
     * @return
     */
    private String fallback_global(){
        return  "线程池:"+Thread.currentThread().getName()+"  系统繁忙，请稍后再试, 通用！！！";
    }
}


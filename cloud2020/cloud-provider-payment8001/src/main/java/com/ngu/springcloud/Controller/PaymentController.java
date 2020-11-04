package com.ngu.springcloud.Controller;

import com.ngu.springcloud.entities.CommonResult;
import com.ngu.springcloud.entities.Payment;
import com.ngu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${logging.path}")
    private String logPath;

    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int id = paymentService.create(payment);
        log.info("****插入结果:"+id);
        if(id>0){
            return new CommonResult(200, "成功,serverPort="+serverPort, id);
        }
        return new CommonResult(444, "失败,serverPort="+serverPort);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果: "+payment);
        System.out.println(logPath);
        if(payment!=null){
            return new CommonResult(200, "成功,serverPort="+serverPort, payment);
        }
        return new CommonResult(444, "失败,serverPort="+serverPort);
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("****element:"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances){
            System.out.println("****:\t"+instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getPort()
            +"\t"+instance.getUri());
        }

        return new CommonResult(200,"成功",services);
    }

    /**
     * 负载均衡
     * @return
     */
    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/feign/timeout")
    public String getPaymentFeignTimeOut() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return serverPort;
    }


    @GetMapping("/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，O(∩_∩)O哈哈~";
    }
}

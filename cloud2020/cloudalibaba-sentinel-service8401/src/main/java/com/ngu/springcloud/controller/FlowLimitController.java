package com.ngu.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController
{
    @GetMapping("/testA")
    public String testA() {
        log.info(Thread.currentThread().getName()+"\t  testA");
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }


    @GetMapping("/testD")
    public String testD() {
//        try {
//            Thread.sleep(1000);
////            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("testD");

        int a = 10/0;
        return "------testD";
    }

    @GetMapping("/hotKey")
    @SentinelResource(value = "hotKey",blockHandler = "deal_hotKey")
    public String hotKey(@RequestParam(value = "p1", required = false) String p1,
                         @RequestParam(value = "p2", required = false) String p2) {
        log.info("******hotKey");
        return "----hotKey";
    }

    // 自定义兜底方法
    public String deal_hotKey(String p1, String p2, BlockException e) {
        log.info("******deal_hotKey");
        return "----deal_hotKey";
    }

}

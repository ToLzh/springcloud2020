package com.ngu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")  // 远程github上 文件中的内容
    private String info;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return info;
    }
}

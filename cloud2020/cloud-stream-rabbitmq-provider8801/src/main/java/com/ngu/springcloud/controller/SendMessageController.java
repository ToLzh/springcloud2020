package com.ngu.springcloud.controller;

import com.ngu.springcloud.service.IMesaageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {

    @Resource
    private IMesaageProvider iMesaageProvider;

    @GetMapping("sendMessage")
    public String sendMessage(){
        return iMesaageProvider.send();
    }
}

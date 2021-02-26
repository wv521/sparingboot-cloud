package com.ww.feign.aop.controller;

import com.ww.feign.aop.service.AopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @Autowired
    private AopService aopService;

    @RequestMapping(value = "/aop")
    public void test(){
        aopService.testA();
//        aopService.testB();
    }
}

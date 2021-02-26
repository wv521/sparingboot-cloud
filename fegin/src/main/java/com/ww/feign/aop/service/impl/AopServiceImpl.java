package com.ww.feign.aop.service.impl;

import com.ww.feign.aop.service.AopService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class AopServiceImpl implements AopService {
    public void testA() {
        System.out.println("我是方法A");
        this.testB();
    }

    public void testB() {
        System.out.println("我是方法B");
    }
}

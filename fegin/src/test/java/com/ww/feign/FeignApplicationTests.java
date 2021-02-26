package com.ww.feign;

import com.ww.feign.aop.proxy.AopProxy;
import com.ww.feign.aop.service.AopService;
import com.ww.feign.aop.service.impl.AopServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@RunWith(SpringRunner.class)
@SpringBootTest
class FeignApplicationTests {

    @Autowired
    private AopService aopService;

    @Test
    void contextLoads() {
        aopService.testA();
//        AopService aopService = new AopServiceImpl();
//        InvocationHandler aopProxy = new AopProxy(aopService);
//        AopService o = (AopService) Proxy.newProxyInstance(aopService.getClass().getClassLoader(), aopService.getClass().getInterfaces(), aopProxy);
//        o.testA();
    }

}

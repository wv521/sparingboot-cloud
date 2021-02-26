package com.ww.feign.aop.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAspect {

    @Pointcut(value = "execution(* com.ww.feign.aop.service.*.*(..))")
    public void AopAspect(){}

    @Before("AopAspect()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("我是前置通知");
    }

    @After("AopAspect()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("我是后置通知");
    }

//    @Around("AopAspect()")
//    public void around(JoinPoint joinPoint){
//        System.out.println("我是环绕通知");
//    }
}

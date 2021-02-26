package com.ww.feign.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AopProxy implements InvocationHandler {

    private Object target;

    public AopProxy(Object object){
        this.target = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target,args);
    }
}

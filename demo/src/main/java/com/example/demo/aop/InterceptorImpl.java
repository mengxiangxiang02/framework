package com.example.demo.aop;

import java.lang.reflect.InvocationTargetException;

public class InterceptorImpl implements Interceptor{
    @Override
    public boolean before() {
        System.out.println("before");
        return true;
    }

    @Override
    public void after() {
        System.out.println("after");

    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("around before");
        Object proceed = invocation.proceed();
        System.out.println("around after");
        return proceed;
    }

    @Override
    public void afterReturn() {
        System.out.println("afterReturn");

    }

    @Override
    public void afterThrow() {
        System.out.println("afterThrow");

    }

    @Override
    public boolean useAround() {
        return true;
    }
}

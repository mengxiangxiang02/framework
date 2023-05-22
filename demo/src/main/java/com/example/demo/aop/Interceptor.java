package com.example.demo.aop;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {
    //事前方法
    public boolean before();

    //事后方法
    public void after();

    /**
     * 取代原有事件方法
     * @param invocation --回调参数，通过他的proceed方法回调原有事件
     * @return 原有事件返回对象
     */
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException ;


    //事件没有发生异常执行
    void afterReturn();

    //事件发生异常执行
    void afterThrow();

    //是否使用around取代原有方法
    boolean useAround();
}

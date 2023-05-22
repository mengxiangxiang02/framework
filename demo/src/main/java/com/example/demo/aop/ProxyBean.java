package com.example.demo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBean implements InvocationHandler {
    private Object target=null;
    private Interceptor interceptor = null;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //异常标识
        boolean exceptionFlag=false;
        Invocation invocation=new Invocation(target,method,args);
        Object retObj=null;
        try{
            if(this.interceptor.before())
            {
                retObj=this.interceptor.around(invocation);
            }else {
                method.invoke(target,args);
            }
        }catch (Throwable t) {
            exceptionFlag = true;
        }
        this.interceptor.after();
        if(exceptionFlag)
        {
            this.interceptor.afterThrow();
        }else {
            this.interceptor.afterReturn();
            return retObj;
        }
        return null;
    }


    /**
     * 返回代理对象
     * @param target 被代理对象
     * @param interceptor 拦截器
     * @return 代理对象
     */
    public static  Object getProxyBean(Object target,Interceptor interceptor){
        ProxyBean proxyBean=new ProxyBean();
        //绑定代理对象
        proxyBean.target=target;
        //绑定拦截器
        proxyBean.interceptor = interceptor;
        //生成代理对象
        Object proxy= Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),proxyBean);
        //返回代理对象
        return proxy;
    }
}

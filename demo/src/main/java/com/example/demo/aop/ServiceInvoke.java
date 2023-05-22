package com.example.demo.aop;

public class ServiceInvoke {
    public static void main(String[] args) throws Exception {
        HelloService helloService=new HelloServiceImpl();
        HelloService proxy=(HelloService) ProxyBean.getProxyBean(helloService,new InterceptorImpl());
        proxy.sayHello("ss");
    }
}

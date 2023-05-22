package com.example.demo.aop;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService{

    @Override
    public void sayHello(String name) {
        if(name==null)
        {
            throw new RuntimeException("name is null");
        }
        System.out.println("hello:"+name);

        HelloService.super.defaultMethod();
    }
}

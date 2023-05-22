package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    /**
     * 其中：•execution表示在执行的时候，拦截里面的正则匹配的方法；　　
     * *表示任意返回类型的方法；
     * HelloServiceImpl指定目标对象的全限定名称；
     * sayHello指定目标对象的方法；
     * •(..)表示任意参数进行匹配。
     */
    @Pointcut(" execution(* HelloServiceImpl.sayHello(..))")
    public  void pointCut()
    {

    }
    @Before("pointCut()&&args(name)")
    public void before(JoinPoint join,String name){
        System.out.println(name);
        System.out.println(join.getArgs()[0]);
        System.out.println("before ......");

    }

    @After("pointCut()")
    public void after() {
        System.out.println("after ......");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning ......");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing ......");
    }
}

package com.example.demo.aop;

public interface HelloService {
    void sayHello(String name);
    public static void staticMethod()
    {
        System.out.println("static method");
    }

    default void defaultMethod()
    {
        System.out.println("default method");
    }
}

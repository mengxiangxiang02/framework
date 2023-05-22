package com.architecture;

public interface DemoDefault {

    public default void  send()
    {
        System.out.println("default method");
    }

    public static void implement()
    {
        System.out.println("static method");
    }

    public abstract void method();

}

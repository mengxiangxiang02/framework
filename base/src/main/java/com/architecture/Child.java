package com.architecture;

import com.architecture.Parent;

public class Child extends Parent {
    public static String s = "child_base";
    public String m = "child";

    public static void staticTest() {
        System.out.println("child static: " + s);
    }
    public int sum(int a,long b)
    {
        int sum=a+b;
        return  sum;
    }

    public static void main(String[] args) {
        Child c = new Child();
        Parent b = c;
        System.out.println(b.s);
        System.out.println(b.m);
        b.staticTest();
        System.out.println(c.s);
        System.out.println(c.m);
        c.staticTest();
    }
}

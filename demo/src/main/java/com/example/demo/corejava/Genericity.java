package com.example.demo.corejava;

public class Genericity {

    public static <T extends Comparable>  void getMiddle(T a)
    {

    }

    public static  void  main(String args[])
    {
        Employee employee=new Employee(12.1,"dd");
        getMiddle(employee);
    }
}

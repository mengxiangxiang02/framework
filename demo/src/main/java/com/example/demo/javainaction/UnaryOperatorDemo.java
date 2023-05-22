package com.example.demo.javainaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class UnaryOperatorDemo {
    public static  void  main(String args[])
    {



        List<Integer> integerList= Arrays.asList(1,2,3,4,5);
        UnaryOperator<Integer> unaryOperator=i->i*i;
        List<Integer> integerListresult = unaryOperatorFun(unaryOperator, integerList);
        System.out.println(integerListresult);
    }

    private static List<Integer> unaryOperatorFun(UnaryOperator<Integer> unaryOperator,List<Integer> integerList)
    {
        List<Integer> list=new ArrayList<>();
        integerList.forEach(i->list.add(unaryOperator.apply(i)));
        return  list;
    }

}

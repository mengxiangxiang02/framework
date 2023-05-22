package com.example.demo.corejava;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

public class TraceHander implements InvocationHandler {
    private Object target;
    public TraceHander(Object t) {
        target = t;
    }
    @Override
    //invoke方法打印出被调用方法的名字和参数，随后用包装好的对象作为隐式参数调用这个方法。
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //print implicit(隐式参数) argument
        System.out.print(target);
        //print method name and parameters
        System.out.print("."+method.getName()+"(");
        //print explicit argument
        if(args!=null)
        {
            for(int i=0;i< args.length;i++)
            {
                System.out.print(args[i]);
                if(i<args.length-1)
                {
                    System.out.print(",");
                }
            }
        }
        System.out.println(")");
        //invoke actual method
        return method.invoke(target, args);
    }


    public static void main(String args[]) {
        Object[] elements = new Object[100];
        Class[] interfaces = new Class[]{Comparable.class};//Class对象数组，每个元素都要实现它
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;//包装的对象
            InvocationHandler invocationHandler = new TraceHander(value);//代理对象调用处理器
            Object proxy = Proxy.newProxyInstance(null, interfaces, invocationHandler);//代理对象
            elements[i] = proxy;
        }
        //construct a random integer
        Integer key=new Random().nextInt(elements.length)+1;

        //search  for the key
        int result=Arrays.binarySearch(elements,key);

        //print match if found
        if(result>0)
        {
            System.out.println(elements[result]);
        }
    }
}

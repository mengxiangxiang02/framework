package com.architecture;

import java.io.Serializable;

/**
 * @author : mengxiangxiang
 * @Date :   2018/12/16
 * @description :讲解class.forName方法的使用
 */
/*
定义一个基类用于继承
 */
class Base{
    Base(){};
    Base(int i){};
}

class Sub extends Base implements Serializable
{
    Sub(){
        super(1);
    }
}


public class ClassForName {
    public static void printlnInfo(Class cc)
    {
        System.out.println("Class name:"+cc.getName());
        System.out.println("simple name:"+cc.getSimpleName());
        System.out.println("canonical name:"+cc.getCanonicalName());//canonical权威的
    }

    public static void main(String args[])
    {
        Class c=null;
        try
        {
            c = Class.forName("com.architecture.Sub");
        }catch (ClassNotFoundException e)
        {
            System.out.println("can't find sub");
            System.exit(1);
        }
        printlnInfo(c);

        //输出类的实现的接口信息
        for(Class face:c.getInterfaces())
        {
            printlnInfo(face);
        }

        //输出类的父类
        Class up=c.getSuperclass();

        Object obj=null;
        try{
            obj=up.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        printlnInfo(obj.getClass());
        System.out.println(obj);
        System.out.println((Base)obj);

    }
}

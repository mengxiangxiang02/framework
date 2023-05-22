package com.example.demo.corejava;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import  java.util.Date;
public class ReflectionTest {
    public static  void  main(String args[]) throws ClassNotFoundException {
        String name="java.util.Date";
        Class<?> className = Class.forName(name);
        Class<?> superclass = className.getSuperclass();
        String modifiers = Modifier.toString(className.getModifiers());
        if(modifiers.length()>0)
        {
            System.out.print(modifiers+" ");
        }
        System.out.print("class "+name);
        if(superclass!=null&&superclass!=Object.class)
        {
            System.out.print(" extends "+superclass.getName());
        }
        System.out.println("\n{\n");
        printFields(className);
        System.out.println("\n");
        printConstructors(className);
        System.out.println("\n");
        printMethods(className);
    }


    /**
     * Prints all fields of a class
     * @param cl a Class
     */
    public static void printFields(Class cl)
    {
        Field[] fields=cl.getDeclaredFields();//getDeclareFields返回类中声明的全部域，其中包括私有和受保护成员，但不包括超类的成员。getFields返回的会包括超类的公有成员
        for(Field f:fields)
        {
            Class type=f.getType();//用来返回描述域所属类型的Class对象
            String name=f.getName();//返回变量名
            System.out.print("  ");
            String modifiers= Modifier.toString(f.getModifiers());//getModifiers将返回一个整型数值，用不同的位开关描述public和static这样的修饰符使用状况
            if(modifiers.length()>0)
            {
                System.out.print(modifiers+" ");
            }
            System.out.println(type.getName()+" "+name+";");
        }
    }

    /**
     * Print all Constructors of a class
     * @param cl a class
     */
    public static  void printConstructors(Class cl)
    {
        Constructor[] constructors=cl.getConstructors();
        for(Constructor constructor:constructors)
        {
            String name=constructor.getName();
            System.out.print("  ");
            String modifiers=Modifier.toString(constructor.getModifiers());
            if(modifiers.length()>0)
            {
                System.out.print(modifiers+" ");
            }
            System.out.print(name+"(");

            Class[] paramTypes=constructor.getParameterTypes();//获取参数泪絮
            for(int j=0;j<paramTypes.length;j++)
            {
                if(j>0) {System.out.print(", ");}
                System.out.print(paramTypes[j].getName());
            }

            System.out.println(");");
        }
    }


    public static void printMethods(Class cl)
    {
        Method[] methods=cl.getDeclaredMethods();
        for(Method method:methods)
        {
            Class retType=method.getReturnType();//获取返回类型
            String name=method.getName();
            System.out.print(" ");
            //print modifiers,return type and method name
            String modifiers=Modifier.toString(method.getModifiers());
            if(modifiers.length()>0)
            {
                System.out.print(modifiers+" ");
            }

            System.out.print(retType.getName()+" "+name+"(");

            Class[] parameterTypes=method.getParameterTypes();
            for(int j=0;j<parameterTypes.length;j++)
            {
                if(j>0) {System.out.print(", ");}
                System.out.print(parameterTypes[j].getName());
            }
            System.out.println(");");

        }
    }
}

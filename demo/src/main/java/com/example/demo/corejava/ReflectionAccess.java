package com.example.demo.corejava;

import com.example.demo.javainaction.Student;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ReflectionAccess {
    public static  void  main(String args[]) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Student student=new Student("mxx",12,"ninth");
        Class cl=student.getClass();
        Field fieldName = cl.getDeclaredField("name");
        fieldName.setAccessible(true);
        /*
        由于name是一个私有域，所以get方法将会抛出一个IllegalAccessException。
        只有利用get方法才能得到可访问域的值。除非拥有访问权限，否则Java安全机制只允许查看任意对象有哪些域，而不允许读取它们的值。
        所以需要setAccessible方法
         */
        Object name = fieldName.get(student);
        System.out.println(name);

        Field fieldAge = cl.getDeclaredField("age");
        fieldAge.setAccessible(true);
        int age = fieldAge.getInt(student);//student是某个包含fieldAge域的类的对象，fieldAge.get(obj)将返回一个对象，其值为student域的当前值
        System.out.println(age);

        //可以获得就可以设置。调用f.set(obj, value)可以将obj对象的f域设置成新值。
        fieldAge.set(student,15);
        System.out.println(student.getAge());

        Class<String> stringClass = String.class;
        Class[] arrayList={String.class,int.class};
        Method setClassName = cl.getDeclaredMethod("setClassName", String.class,int.class);//
        Object tenth = setClassName.invoke(student, "tenth",12);
        System.out.println(tenth);


        Method getClassName = cl.getDeclaredMethod("getClassName");
        Object invoke = getClassName.invoke(student);
        System.out.println(invoke);

    }
}

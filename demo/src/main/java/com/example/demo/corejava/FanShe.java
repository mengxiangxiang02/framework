package com.example.demo.corejava;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FanShe {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> l =new ArrayList<>();
        Map<String,String> h =new HashMap<>();
        Class<?> a = new TestFanShe().getClass();
        a.getMethod("say",  Map.class,Map.class,Map.class).invoke(new TestFanShe(),h,h,h);
        a.getMethod("say",  new Class<?>[] {Map.class,Map.class,Map.class}).invoke(new TestFanShe(),new Object[]{h,h,h});


    }
}
class TestFanShe{
    public void say(Map<String,String> map,Map<String,String> map1,Map<String,String> map2){
        System.out.println("aaa");
    }
}

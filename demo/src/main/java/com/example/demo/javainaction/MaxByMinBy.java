package com.example.demo.javainaction;

import java.lang.reflect.Proxy;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class MaxByMinBy {
    public static void main(String[] args) {
        Student s1 = new Student("Shyam", 22,"A");
        Student s2 = new Student("Ram",23,"A");
        Student s3 = new Student("Mohan",22,"B");
        Student s4 = new Student("Ramesh",21,"B");
        List<Student> list = Arrays.asList(s1,s2,s3,s4);
        Comparator<Student> ageComparator=Comparator.comparing((Student s)->s.getAge());

        Map<String, Optional<Student>> collect = list.stream().collect(
                Collectors.groupingBy((Student::getClassName), Collectors.reducing(BinaryOperator.maxBy(ageComparator)))
        );
       for(Map.Entry<String, Optional<Student>> set:collect.entrySet()){
           System.out.println(set.getKey());
           System.out.println(set.getValue().get().getName());
       }
        collect.forEach((k,v)->{
            System.out.println("Class:"+k+" Age:"+v.get().getName());
        });

        String[] strings=new String[50];
    }


    public static <T extends Comparator>  T getMax(List<T> list)
    {
        return  list.get(0);

    }
}

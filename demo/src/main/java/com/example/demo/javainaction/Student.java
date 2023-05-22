package com.example.demo.javainaction;

public class Student {
    private String name;
    private int age;
    private String className;
    public Student(String name,Integer age, String className){
        this.name=name;
        this.age=age;
        this.className = className;
    }
    public String getName() {
        return name;
    }
    public Integer getAge() {
        return age;
    }
    public String getClassName() {
        return className;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String setClassName(String className,int age) {
        this.className = className;
        return className+age;
    }
}

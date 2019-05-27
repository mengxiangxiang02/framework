package com.hession.hessionapi.bean;

import java.io.Serializable;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/11 14:01
 * @Description:
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 8920334305734912423L;
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

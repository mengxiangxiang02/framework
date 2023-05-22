package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class Dog implements Animal {
    @Value("${spring.datasource.url}")
    private String url=null;
    @Override
    public void use() {
        System.out.println(url);
        System.out.println("狗【" + Dog.class.getSimpleName()+"】是看门用的。");
    }
}
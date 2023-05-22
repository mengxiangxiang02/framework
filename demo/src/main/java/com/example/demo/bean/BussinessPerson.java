package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BussinessPerson implements Person {
    private Animal animal =null;

    @Override
    public void service() {
        this.animal.use();
    }

    public BussinessPerson(@Autowired  @Qualifier("dog") Animal animal) {
        this.animal = animal;
    }

}

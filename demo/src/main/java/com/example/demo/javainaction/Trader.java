package com.example.demo.javainaction;

import com.sun.istack.internal.NotNull;
import org.aspectj.lang.annotation.DeclareMixin;

public class Trader{
    @NotNull

    private final String name;
    private final String city;
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }
    @Override
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}
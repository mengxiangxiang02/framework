package com.architecture.java8.defaultimplement;

/**
 * @author : mengxiangxiang
 * @Date :   2019/1/1
 * @description :java8新特性默认实现接口的方法
 */
public interface  DefaultFunctionInterface {
    default String defaultFunction(){
        return "default function";
    }
    static String staticFunction() {
        return "static function";
    }
}

package com.architecture.decorator;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/6 16:28
 * @Description:定义一个抽象类用于继承及修饰
 * 学校成绩单抽象类
 */
public  abstract  class SchoolReport {

    //展示成绩单
    public abstract void report();
    //家长签字
    public abstract void sign(String name);
}

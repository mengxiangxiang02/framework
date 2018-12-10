package com.architecture.decorator;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/6 16:44
 * @Description:添加另外一个具体的修饰类
 */
public class SortDecorator extends Decorator {
    public SortDecorator(SchoolReport schoolReport) {
        super(schoolReport);
    }

    //添加具体的修饰方法
    public void reportSort()
    {
        System.out.println("我是排名第38名...");

    }

    @Override
    public void report()
    {
        super.report();
        this.reportSort();

    }

}

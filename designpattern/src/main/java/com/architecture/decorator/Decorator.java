package com.architecture.decorator;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/6 16:34
 * @Description:增加一个修饰抽象类，实现要修饰的类的方法
 */
public abstract  class Decorator  extends SchoolReport{
    //添加需要修饰的类
    private SchoolReport schoolReport;


    public Decorator(SchoolReport schoolReport)
    {
        this.schoolReport=schoolReport;
    }
    //成绩单还是要被看到的
    @Override
    public void report()
    {
        this.schoolReport.report();
    }
    @Override
    public void sign(String name)
    {
        this.schoolReport.sign(name);
    }

}

package com.architecture.decorator;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/6 16:40
 * @Description:添加一个修饰类的具体修饰类
 */
public class HighScoreDecorator extends Decorator{

    public HighScoreDecorator(SchoolReport schoolReport) {
        super(schoolReport);
    }
    //添加要修饰的具体行为方法
    public void reportHignScore()
    {
        System.out.println("这次考试语文最高是75，数学是78，自然是80");
    }

    @Override
    public void report()
    {
        //添加要修饰的具体行为
        this.reportHignScore();
        super.report();
    }
}

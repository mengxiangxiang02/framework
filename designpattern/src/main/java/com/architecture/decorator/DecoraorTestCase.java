package com.architecture.decorator;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/6 16:49
 * @Description:测试用例
 */
public class DecoraorTestCase {
    public static void main(String args[])
    {
        SchoolReport sr=new GradeSchoolReport();
        sr=new HighScoreDecorator(sr);
        //sr=new SortDecorator(sr);
        sr.report();
        sr.sign("孟祥祥");
    }
}

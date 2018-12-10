package com.architecture.decorator;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/6 16:32
 * @Description:具体的成绩单只实现基本的功能，不加修饰
 */
public class GradeSchoolReport extends SchoolReport{
    @Override
    public void report() {
        //成绩单的格式是这个样子的
        System.out.println("尊敬的XXX家长:");
        System.out.println(" ......");
        System.out.println(" 语文 62 数学65 体育 98 自然 63");
        System.out.println(" .......");
    }

    @Override
    public void sign(String name) {
        System.out.println("家长签名为："+name);
    }
}

package com.architecture.java8.Stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : mengxiangxiang
 * @Date :   2019/1/1
 * @description :Stream使用
 */
public class StreamUse {
    public static void  main(String args[])
    {
        List<Integer> nums =new ArrayList<>();
        nums.add(1);
        nums.add(null);
        nums.add(2);
        long count = nums.stream().filter(num -> num != null).count();
        System.out.println(count);
    }
}

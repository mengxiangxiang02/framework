package com.architecture;

public class MyStery {
    public static String myStery(String s)
    {
        //如果不能拆分则返回本身
        int length=s.length();
        if(length<=1)
        {
            return s;
        }
        //如果能拆分，将其拆分成两部分，将两部分替换顺序
        String before=s.substring(0,length/2);
        String after=s.substring(length/2,length);
        return myStery(after)+myStery(before);
    }

    public static  void main(String args[])
    {
        String s="abcdefg";
        System.out.println(myStery(s));
    }
}

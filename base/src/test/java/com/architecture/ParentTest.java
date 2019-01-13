package com.architecture;

public class ParentTest {
    static  class parent{
        public static int A=1;
        static{
            A=2;
        }
    }
    static  class Sub extends  parent{
        public static int B=A;
    }

    public static void main(String args[])
    {
        System.out.println(Sub.A);
        System.out.println(parent.A);
    }

}

package com.example.demo.corejava;

public class RegexTest {
    public static void main(String args[]) {
        String regexTest = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
        String testString="01";
        boolean result=testString.matches(regexTest);
        System.out.println(result);

        String regexqqEmail="^[0-9]{9,}@qq\\.com$";
        String qqEmail="836671950@qq.com";
        boolean matchResult=qqEmail.matches(regexqqEmail);
        System.out.println(matchResult);
    }
}

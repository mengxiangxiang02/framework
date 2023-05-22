package com.example.demo.corejava;

import java.io.*;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String args[]) throws IOException {
        Properties properties=new Properties();
        properties.setProperty("width","200");
        properties.setProperty("title","hello world");
        //查找一个字符串的值时可以指定一个默认值，这样当键不存在时就会自动使用这个默认值。
        properties.getProperty("title","Default title");
        //可以使用store方法将属性映射列表保存到一个文件中。在这里，我们将属性映射保存在文件program.properties中。第二个参数是包含在这个文件中的注释。
        OutputStream out=new FileOutputStream("D://program.properties");
        properties.store(out,"program.properties");

        //要从文件加载属性，可以使用以下调用：
        InputStream in=new FileInputStream("D://program.properties");
        properties.load(in);

        //要找出用户的主目录，可以调用System.getProperties方法，它恰好也使用一个Properties对象描述系统信息。主目录包含键"user.home"。
        String userDir = System.getProperty("user.home");
        System.out.println(userDir);
        //获取所有系统属性
        Properties systemProperties = System.getProperties();
        System.out.println(systemProperties);
    }
}

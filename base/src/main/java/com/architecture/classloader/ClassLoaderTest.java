package com.architecture.classloader;

import com.architecture.gc.Finalize;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void  main(String args[]) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader=new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName=name.substring(name.lastIndexOf(".")+1)+".class";
                InputStream inputStream=getClass().getResourceAsStream(fileName);
                if(inputStream ==null)
                {
                    return  super.loadClass(name);
                }
                try {
                    byte[] bytes=new byte[inputStream.available()];
                    inputStream.read(bytes);
                    return defineClass(name,bytes,0,bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };

        Object aClass = classLoader.loadClass("com.architecture.gc.Finalize").newInstance();
        System.out.println(aClass.getClass());
        System.out.println(aClass instanceof Finalize);
    }
}

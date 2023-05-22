package com.example.demo.corejava;

import com.itextpdf.text.log.SysoCounter;

public class ThreadTest {
    public static  void main(String args[])
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread start");
                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("thread end");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        //join方法是让主线程执行完run方法后  主线程再执行join()后面的代码
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("main method run");

    }
}

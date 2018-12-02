package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author : mengxiangxiang
 * @Date :   2018/10/28
 * @description :捕获异常
 */
class ExceptionThread implements Runnable
{
    public void run()
    {
        throw  new RuntimeException();
    }
}
class ExceptionThread2 implements Runnable
{
    public void run()
    {
        Thread t=Thread.currentThread();
        System.out.println("run() by"+t);
        System.out.println("eh="+t.getUncaughtExceptionHandler());
        throw  new RuntimeException();
    }
}
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caughted:"+e);
    }
}
class HandlerThreadFactory implements ThreadFactory
{

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this+":is creating new thread");
        Thread t=new Thread(r);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh="+t.getUncaughtExceptionHandler());
        System.out.println("create thread:"+t);
        return t;
    }
}
public class CatchException {
    public static void main(String args[])
    {
        ExecutorService exec= Executors.newFixedThreadPool(2);
        for(int i=0;i<10;i++)
        {
            exec.execute(new ExceptionThread2());
        }

    }
    public static void exceptionHandler()
    {
        ExecutorService exec= Executors.newCachedThreadPool(new HandlerThreadFactory());

        exec.execute(new ExceptionThread2());

        exec.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------");

        /*或者创建默认的线程处理器*/
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(new ExceptionThread());

        executorService.shutdown();
    }
}

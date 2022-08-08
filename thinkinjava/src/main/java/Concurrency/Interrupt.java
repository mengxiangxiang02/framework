package Concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author : mengxiangxiang
 * @Date :   2018/11/1
 * @description :如果想调用线程的interrupt 可以使用Thread.interrupt()方法，如果使用Executor，那么使用submit()方法
 * 启动任务是可以持有该任务的上下文，submit()方法返回一个泛型Future<?> 在Future上调用cancel()方法，将true传递给cancel(),它
 * 就拥有在该线程上调用interrupt() 以停止这个线程的权限。
 */
/*
先定义三个任务，并使其阻塞，然后使用 Future的cancel() 调用中断方法，看能否使其中断
 */
class sleepBlocked implements  Runnable
{
    public void run()
    {
        try
        {
            TimeUnit.SECONDS.sleep(100);
        }catch (InterruptedException e)
        {
            System.out.println("异常"+e);
        }
        System.out.println("Exiting SleepBlocked.run()");
    }
}
class IOBlocked implements Runnable
{
    private InputStream in;
    public  IOBlocked(InputStream is)
    {
        in=is;
    }
    public  void run()
    {
        try
        {
            in.read();
        }catch (IOException e)
        {
            System.out.println(e);
            if(Thread.currentThread().isInterrupted())
            {
                System.out.println("Interrupted from IOBlocked");
            }
            else {
                throw  new RuntimeException(e);
            }
        }
    }
}

class  SyncronizedBlocked implements Runnable
{
    public synchronized void f()
    {
        while (true)
        {
            Thread.yield();
        }
    }
    public SyncronizedBlocked()
    {
        new Thread(){
            public void run()
            {
                f();
            }
        }.start();
    }

    public void run()
    {
        System.out.println("Trying to call f()");
        f();
        System.out.println("Exiting SyncronizedBlocked.run()");
    }
}

public class Interrupt {
    private static ExecutorService exec= Executors.newCachedThreadPool();
    static void test(Runnable runnable) throws InterruptedException {
        Future<?> f=exec.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Interrupting :"+runnable.getClass().getName());
        f.cancel(true);
        System.out.println("Interrupt to :"+runnable.getClass().getName());

    }

    public static  void main(String[] args) throws InterruptedException, IOException {
        /*test(new sleepBlocked());
        test(new IOBlocked(System.in));
        test(new SyncronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);*/
        closeStream();
    }

    /*
    解除I/O流的阻塞：通过关闭流
     */
    public static void closeStream() throws IOException, InterruptedException {
        ExecutorService exec=Executors.newCachedThreadPool();
        ServerSocket server=new ServerSocket(8080);
        InputStream sockedInput=new Socket("localhost",8080).getInputStream();
        exec.execute(new IOBlocked(sockedInput));
        exec.execute(new IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Shutting down all threads");
        exec.shutdownNow();

        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("Closinng "+sockedInput.getClass().getName());
        sockedInput.close();

        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("Closinng "+System.in.getClass().getName());
        System.in.close();



        System.exit(0);
    }
}

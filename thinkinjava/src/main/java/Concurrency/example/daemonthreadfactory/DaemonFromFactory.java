package Concurrency.example.daemonthreadfactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : mengxiangxiang
 * @Date :   2018/10/27
 * @description :
 */
public class DaemonFromFactory implements Runnable {
    public  void run(){
        try{
            while(true)
            {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread()+" "+this);
            }
        }catch (InterruptedException e)
        {
            System.out.println(e);
        }
    }
    public  static void main(String[] arg) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool(new DaemonThreadFactory());
        for(int i=0;i<5;i++)
        {
            executorService.execute(new DaemonFromFactory());
        }
        TimeUnit.MILLISECONDS.sleep(1000);
        executorService.shutdown();
    }
}

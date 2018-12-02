package Concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : mengxiangxiang
 * @Date :   2018/10/28
 * @description :定义线程安全的原子类
 */
public class Atomic implements Runnable{
    private AtomicInteger i=new AtomicInteger(0);
    public int getValue(){
        return i.get();
    }
    private int evenIncrement()
    {
        int i = this.i.addAndGet(2);//得到一个偶数
        System.out.println(i);
        return i;
    }
    public void run()
    {
        while(true) {
            evenIncrement();
        }
    }

    public static void main(String[] args)
    {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        },50);
        ExecutorService exec= Executors.newCachedThreadPool();
        Atomic ait=new Atomic();
        exec.execute(ait);
        while(true)
        {
            int val=ait.getValue();
            if(val%2!=0)
            {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}

package Concurrency;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : mengxiangxiang
 * @Date :   2018/11/4
 * @description :哲学家就餐问题
 * 死锁的四个条件：1 互斥条件 。使用的资源至少有一个不能共享。
 * 2：持有一个资源，等待另外一个被别的任务占有的资源
 * 3：资源不能抢占
 * 4：循环等待。
 */
class ChopStick
{
    private boolean token=false;
    public synchronized void take() throws InterruptedException {
        while(token) wait();
        token=true;
    }

    public synchronized void drop()
    {
        token=false;
        notifyAll();
    }
}
class Philosopher implements Runnable
{
    private ChopStick left;
    private ChopStick right;
    private int id;
    private int ponderFactor;
    private Random rand=new Random(47);
    public void pause() throws InterruptedException {
        if(ponderFactor==0) return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor*250));
    }
    public Philosopher(ChopStick left,ChopStick right,int ident,int ponderFactor)
    {
        this.left=left;
        this.right=right;
        id=ident;
        this.ponderFactor=ponderFactor;
    }
    public  void run()
    {
        try
        {
            while(!Thread.interrupted())
            {
                System.out.println(this+"Thinking");
                pause();
                System.out.println(this +" "+"Grabing right");
                right.take();
                pause();
                System.out.println(this +" "+"Grabing left");
                left.take();
                System.out.println(this+" "+"eating");
                pause();
                right.drop();
                left.drop();
            }
        }catch (InterruptedException e)
        {
            System.out.println(this+" "+"exiting via interrupt");

        }
    }
    public String toString()
    {
        return "Philosopher"+id;
    }
}
public class DeadLock {
    public static void main(String args[]) throws IOException {
        int ponder=1;
        int size=5;
        ExecutorService exec= Executors.newCachedThreadPool();
        ChopStick[] sticks=new ChopStick[size];
        for(int i=0;i<size;i++)
        {
            sticks[i]=new ChopStick();
        }
        for(int i=0;i<size;i++)
        {
            exec.execute(new Philosopher(sticks[i],sticks[(i+1)%size],i,ponder));
        }
        System.out.println("Press 'Enter' to quit");
        System.in.read();
        exec.shutdownNow();
    }

}

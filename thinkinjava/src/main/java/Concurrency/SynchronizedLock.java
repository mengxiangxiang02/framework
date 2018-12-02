package Concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : mengxiangxiang
 * @Date :   2018/10/28
 * @description :并发机制
 */
public class SynchronizedLock {
    private int currentValue=0;//并发的时候，私有化防止其他任务可以直接访问

    /*
        所有的非静态同步方法用的都是同一把锁——实例对象本身，
        也就是说如果一个实例对象的非静态同步方法获取锁后，
        该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，
        可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
        所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。

        而所有的静态同步方法用的也是同一把锁——类对象本身，
        这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
        但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
        而不管是同一个实例对象的静态同步方法之间，还是不同的实例对象的静态同步方法之间，
        只要它们同一个类的实例对象！*
     */
    /*使用关键字实现同步*/
    public synchronized int getCurrentValue() {
        ++currentValue;//不是原子性操作
        ++currentValue;
        return currentValue;
    }

    private Lock lock=new ReentrantLock();
    public int getCurrentValueLock()
    {
        lock.lock();
        try
        {
            ++currentValue;//不是原子性操作
            ++currentValue;
            return currentValue;//在try里面返回，防止锁释放后再返回
        }finally {
            lock.unlock();
        }
    }
    public void untimed()
    {
        boolean captured=lock.tryLock();
        try
        {
            System.out.println("tryLock():"+captured);
        }finally {
            if(captured)
                lock.unlock();
        }
    }

    public void timed()
    {
        boolean captured=false;
        try
        {
            captured=lock.tryLock(2, TimeUnit.SECONDS);
        }catch (InterruptedException e)
        {
            throw  new RuntimeException(e);
        }
        try
        {
            System.out.println("tryLock():"+captured);
        }finally {
            if(captured) lock.unlock();
        }
    }
    public static void main(String[] a) throws InterruptedException {
        final SynchronizedLock al=new SynchronizedLock();
        al.untimed();;
        al.timed();

        new Thread(){
            {setDaemon(true);}
            public void run()
            {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();

        Thread.yield();
        Thread.sleep(100);
        al.untimed();//false
        al.timed();//false
    }
}

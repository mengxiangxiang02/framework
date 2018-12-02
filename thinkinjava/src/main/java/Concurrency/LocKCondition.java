package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : mengxiangxiang
 * @Date :   2018/11/3
 * @description :使用互斥并允许任务挂起的类是Condition
 */
class Car
{
    private Lock lock=new ReentrantLock();
    //condition用来管理任务间的通信。
    private Condition condition=lock.newCondition();
    private boolean waxOn=false;// 表示处理状态的信息
    public void waxed()
    {
        lock.lock();
        try
        {
            waxOn=true;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    public void buffed()
    {
        lock.lock();
        try
        {
            waxOn=false;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try
        {
            while(waxOn==false) condition.await();
        }finally {
            lock.unlock();
        }
    }
    public void waitforBuffing() throws InterruptedException {
        lock.lock();
        try
        {
            while(waxOn==true) condition.await();
        }finally {
            lock.unlock();
        }
    }
}
class  WaxOn implements Runnable
{
    private Car car;
    public WaxOn(Car car)
    {
        this.car=car;
    }
    public  void run()
    {
        try
        {
            while(!Thread.interrupted())
            {
                System.out.println("Wax on");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitforBuffing();
            }
        }catch (InterruptedException e)
        {
            System.out.println("Exiting vir interrupt");
        }
    }
}
class WaxOff implements Runnable
{
    private Car car;
    public WaxOff(Car car)
    {
        this.car=car;
    }
    public void run()
    {
        try
        {
            while(!Thread.interrupted())
            {
                car.waitforBuffing();
                System.out.println("Waxoff");
                TimeUnit.MILLISECONDS.sleep(100);
                car.buffed();
            }
        }catch (InterruptedException e)
        {
            System.out.println("Exiting vir interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}
public class LocKCondition {
    public static void main(String args[]) throws InterruptedException {
        Car car=new Car();
        ExecutorService exec= Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}

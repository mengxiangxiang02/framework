package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : mengxiangxiang
 * @Date :   2018/11/3
 * @description : 生产者消费者模式
 */
class Meal
{
    private  int orderNum;
    public Meal(int orderNum){this.orderNum=orderNum;}
    public  String toString()
    {
        return "Meal "+orderNum;
    }
}
class WaitPerson implements Runnable
{
    private Restaurant restaurant;
    public WaitPerson (Restaurant restaurant)
    {
        this.restaurant=restaurant;
    }
    public void run()
    {
        try
        {
            while(!Thread.interrupted())
            {
                synchronized (this)
                {
                    while(restaurant.meal==null)
                    {
                        wait(); //。。。forthe chef to produce a meal
                    }
                }
                System.out.println("waitperson got"+restaurant.meal);
                synchronized (restaurant.chef)
                {
                    restaurant.meal=null;
                    restaurant.chef.notifyAll();
                }
            }
        }catch (InterruptedException e)
        {
            System.out.println("WaitPerson interrupted");
        }
    }
}
class Chef implements Runnable
{
    private Restaurant restaurant;
    private int count=0;
    public Chef(Restaurant restaurant)
    {
        this.restaurant=restaurant;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait();
                    }
                }

                if (++count == 10) {
                    System.out.println("closing ");
                    restaurant.waitPerson.notifyAll();
                }

                System.out.println("order up");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}
public class Restaurant {
    Meal meal=null;
    ExecutorService exec= Executors.newCachedThreadPool();
    Chef chef=new Chef(this);
    WaitPerson waitPerson=new WaitPerson(this);
    public Restaurant(){
        exec.execute(chef);
        exec.execute(waitPerson);
    }
    public static void main(String args[])
    {
        new Restaurant();
    }
}

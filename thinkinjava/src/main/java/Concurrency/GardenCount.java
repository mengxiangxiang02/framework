package Concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : mengxiangxiang
 * @Date :   2018/10/31
 * @description :统计花园5个门进入的总人数
 */
class Count
{
    private int count=0;
    private Random rand=new Random(47);
    public synchronized int increment()
    {
        int temp=count;
        if(rand.nextBoolean()) Thread.yield();
        return count=++temp;
    }
    public synchronized int value(){
        return count;
    }
}
class Entrance implements  Runnable
{
    private static Count count=new Count();//定义一个数量的对象
    private static List<Entrance> entranceList=new ArrayList<Entrance>();//定一个所有门的集合 用户汇总数量
    private int number=0;//定义每一个门的数量
    private int id=0;//定义门的标识
    private static volatile boolean canceled=false;

    public static void cancel()
    {
        canceled=true;
    }

    public Entrance(int id)
    {
        this.id=id;
        entranceList.add(this);
    }

    public void run(){
        while(!canceled){
            synchronized(this)
            {
                ++number;//此门进入的人数加一
            }

            System.out.println(this+"total: "+count.increment());//总人数加一

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopping " +this);
    }

    public synchronized int getValue(){return number;}

    public String toString()
    {
        return "Entrance "+id + ": "+getValue();
    }
    /*得到进入公园的总数*/
    public static int getTotalCount()
    {
        return count.value();
    }

    public static int sumEntrances()
    {
        int sum=0;
        for(Entrance entrance:entranceList)
        {
            sum+=entrance.getValue();
        }
        return sum;
    }
}

public class GardenCount {
    public static  void main(String [] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++)
        {
            executorService.execute(new Entrance(i));
        }

        TimeUnit.SECONDS.sleep(2);

        Entrance.cancel();

        executorService.shutdown();

        if(!executorService.awaitTermination(200,TimeUnit.MILLISECONDS))
        {
            System.out.println("some task not terminated");
        }

        System.out.println("Total: " +Entrance.getTotalCount());
        System.out.println("Sum of Entrances: "+Entrance.sumEntrances());
    }

}

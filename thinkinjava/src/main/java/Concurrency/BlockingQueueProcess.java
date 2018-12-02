package Concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : mengxiangxiang
 * @Date :   2018/11/4
 * @description :使用阻塞队列处理组装线的流程
 */
class Toast{
    public enum Status{DRY,BUFFEED,JAMMED}
    private Status status=Status.DRY;
    private int id;
    public Toast(int idn)
    {
        id=idn;
    }
    public void butter()
    {
        status=Status.BUFFEED;
    }
    public void jam()
    {
        status=Status.JAMMED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Toast "+id+" :"+status;
    }
}
class ToastQueue extends LinkedBlockingQueue<Toast>{}
class Toaster implements Runnable{
    private ToastQueue toastQueue;
    private int count=0;
    private Random rand=new Random(47);
    public Toaster(ToastQueue tq)
    {
        toastQueue=tq;
    }

    public void run()
    {
        try
        {
            while(!Thread.interrupted())
            {
                TimeUnit.MILLISECONDS.sleep(100+rand.nextInt(50));
                Toast toast=new Toast(count++);
                toastQueue.put(toast);
            }
        }catch (InterruptedException e)
        {
            System.out.println("Toaster interrupted");
        }
    }
}
class Butter implements Runnable
{
    private ToastQueue dryQueue,butterQueue;
    public Butter(ToastQueue dryQueue,ToastQueue butterQueue)
    {
        this.dryQueue=dryQueue;
        this.butterQueue=butterQueue;
    }
    public void run()
    {
        try
        {
            while(!Thread.interrupted())
            {
               Toast toast=dryQueue.take();
               toast.butter();
               System.out.println(toast);
               butterQueue.put(toast);
            }
        }catch (InterruptedException e)
        {
            System.out.println("Toaster interrupted");
        }
    }

}


class Jammer implements Runnable
{
    private ToastQueue butteredQueue,finishedQueue;
    public Jammer(ToastQueue butteredQueue,ToastQueue finishedQueue)
    {
        this.butteredQueue=butteredQueue;
        this.finishedQueue=finishedQueue;
    }
    public void run()
    {
        try
        {
            while(!Thread.interrupted())
            {
                Toast toast=butteredQueue.take();
                toast.jam();
                System.out.println(toast);
                finishedQueue.put(toast);
            }
        }catch (InterruptedException e)
        {
            System.out.println("Jammer interrupted");
        }
    }
}
class Eater implements Runnable
{
    private ToastQueue finishedQueue;
    private int counter;
    public  Eater(ToastQueue finishedQueue)
    {
        this.finishedQueue=finishedQueue;
    }
    public void run()
    {
        try
        {
            while(!Thread.interrupted())
            {
               Toast toast=finishedQueue.take();
               if(toast.getId()!=counter++||toast.getStatus()!=Toast.Status.JAMMED)
               {
                   System.out.println("Toast error");
                   System.exit(1);
               }else
               {
                   System.out.println("Chomp :"+toast);
               }
            }
            System.out.println("Eater off");
        }catch (InterruptedException e)
        {
            System.out.println("Eater interrupted");
        }
    }
}
public class BlockingQueueProcess {
    public  static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue=new ToastQueue(),
                butteredQueue=new ToastQueue(),
                finishedQueue=new ToastQueue();
        ExecutorService exec= Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butter(dryQueue,butteredQueue));
        exec.execute(new Jammer(butteredQueue,finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }

}

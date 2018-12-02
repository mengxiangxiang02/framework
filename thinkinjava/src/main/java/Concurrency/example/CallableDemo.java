package Concurrency.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : mengxiangxiang
 * @Date :   2018/10/17
 * @description :
 */
class TaskWithResult implements Callable<String>
{
    private  int id;
    public  TaskWithResult(int id)
    {
        this.id=id;
    }
    public String call()
    {
        try {
            int sleep = new Random().nextInt(1000);
            Thread.sleep(sleep);
            System.out.println("sleep seconds"+sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "result of TaskWithResult"+id;
    }
}

public class CallableDemo
{

    public static void main(String args[])
    {
        ExecutorService exec= Executors.newCachedThreadPool();
        ArrayList<Future<String>> result=new ArrayList<Future<String>>();
        for(int i=0;i<10;i++)
        {
            result.add(exec.submit(new TaskWithResult(i)));
        }
        exec.shutdown();
        System.out.println("begin get result");
        for(Future<String> future:result)
        {
            try
            {
                //if(future.isDone())
                {
                    String s = future.get();//get会阻塞 直到获取到该任务的结果
                    System.out.println(s);
                }
            }catch (InterruptedException e)
            {
                e.printStackTrace();
                return;
            }catch (Exception e)
            {
                e.printStackTrace();
                return;
            }
        }
        System.out.println(" get result end");
    }

}

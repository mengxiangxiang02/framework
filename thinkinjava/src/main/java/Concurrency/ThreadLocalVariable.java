package Concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : mengxiangxiang
 * @Date :   2018/10/30
 * @description :线程本地储存
 */
class Accessor implements Runnable{
    private final int id;
    public Accessor(int idn)
    {
        id=idn;
    }
    public void run()
    {
        ThreadLocalVariable.increment();
        ThreadLocalVariable.setMap("id",id+"");
        System.out.println(this);
        Thread.yield();
    }
    public String toString()
    {
        return "thread:"+id+";localint:"+ThreadLocalVariable.get()+";map:"+ThreadLocalVariable.localMapGet();
    }
}


public class ThreadLocalVariable {
    private static ThreadLocal<Integer> value=new ThreadLocal<Integer>(){
      private Random random=new Random(47);
      protected synchronized Integer initialValue(){
          return random.nextInt(10000);
      }
    };

    private static ThreadLocal<Map<String,String>> localMap=new ThreadLocal<Map<String,String>>(){
        protected synchronized Map<String,String> initialValue(){
            return new HashMap<>();
        }
    };
    public static Map<String,String> localMapGet()
    {
        return localMap.get();
    }
    public static void setMap(String key,String value)
    {
        Map<String,String> map=localMap.get();
        map.put(key, value);
        localMap.set(map);
    }
    public static int get(){
        return value.get();
    }
    public static void increment()
    {
        value.set(value.get()+1);
    }

    public static void main(String args[]) throws InterruptedException {
        ExecutorService exec= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++)
        {
            exec.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3);
        exec.shutdown();
    }
}

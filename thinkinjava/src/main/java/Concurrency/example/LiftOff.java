package Concurrency.example;

/**
 * @author : mengxiangxiang
 * @Date :   2018/10/23
 * @description : 定义一个任务：发射倒计时
 */
public class LiftOff  implements Runnable{
    protected int countDown=10;//默认值
    private static int taskCount=0;
    private final int id=taskCount++;

    public LiftOff()
    {}
    public LiftOff(int countDown)
    {
        this.countDown=countDown;
    }
    public String status()
    {
        return "任务数"+id+"("+(countDown>0?countDown:"liftOff finish!")+")";
    }
    @Override
    public void run() {
        while(countDown-->0)
        {
            System.out.println(status());
            Thread.yield();
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    public static void main(String args[])
    {
        //将任务分配给main线程直接执行
//        LiftOff liftOff=new LiftOff();
//        liftOff.run();

        //将任务提交给一个Thread构造器
//        Thread thread=new Thread(new LiftOff());
//        thread.start();

        //定义一个循环建立多个线程
        for(int i=0;i<5;i++)
        {
            Thread thread=new Thread(new LiftOff());
            thread.start();
        }
    }
}

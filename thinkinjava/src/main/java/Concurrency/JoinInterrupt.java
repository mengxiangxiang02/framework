package Concurrency;


/**
 * @author : mengxiangxiang
 * @Date :   2018/10/27
 * @description : 线程加入中断
 */
class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int sleeptime) {
        super(name);
        duration = sleeptime;
        start();
    }

    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + "was interrupted" + "isInterrupter():" + isInterrupted());
            e.printStackTrace();
            return;
        }
        System.out.println(getName() + "has awakened");
    }

}

class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("Interrutped");
        }
        System.out.println(getName()+"join completed");
    }
}

public class JoinInterrupt {

    public static void main(String[] a) {
        Sleeper sleepy = new Sleeper("Sleepy", 1500);
        Sleeper grumpy = new Sleeper("Grumpy", 1500);
        Joiner dopery=new Joiner("Dopery",sleepy);
        Joiner doc=new Joiner("Doc",grumpy);
        grumpy.interrupt();;


    }
}

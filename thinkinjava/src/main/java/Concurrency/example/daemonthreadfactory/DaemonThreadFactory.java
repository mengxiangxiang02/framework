package Concurrency.example.daemonthreadfactory;

import java.util.concurrent.ThreadFactory;

/**
 * @author : mengxiangxiang
 * @Date :   2018/10/27
 * @description :后台线程工厂
 */
public class DaemonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t=new Thread(r);
        t.setDaemon(true);
        return t;
    }
}

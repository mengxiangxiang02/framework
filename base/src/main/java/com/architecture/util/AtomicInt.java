package com.architecture.util;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInt {
    public final static int STAT_INIT = 0;

    protected final static int STAT_RUNNING = 1;

    protected final static int STAT_STOPPED = 2;

    //线程安全的
    public  static AtomicInteger stat = new AtomicInteger(STAT_INIT);

    public static void main(String args[])
    {
        System.out.println(AtomicInt.stat.addAndGet(5));
        System.out.println(AtomicInt.stat.addAndGet(3));
        System.out.println(AtomicInt.stat.getAndIncrement());
        System.out.println(AtomicInt.stat.incrementAndGet());
    }
}

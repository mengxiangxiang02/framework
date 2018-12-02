package Concurrency.exericise;

import sun.nio.cs.Surrogate;

import java.util.Arrays;

/**
 * @author : mengxiangxiang
 * @Date :   2018/10/27
 * @description :
 */
public class FibonacciRunnable implements Runnable {
    private  int n;

    public  FibonacciRunnable(int n)
    {
        this.n=n;
    }

    private int fib(int i)
    {
        if(i<2) return 1;
        return fib(i-1)+fib(i-2);
    }
    public void run()
    {
        Integer[] sequence=new Integer[n];
        for(int i=0;i<n;i++)
        {
            sequence[i]=fib(i);
        }
        System.out.println("number:"+n+";sequence:"+ Arrays.toString(sequence));
    }

    public static void main(String[] a)
    {
        for(int i=0;i<10;i++)
        {
            new Thread(new FibonacciRunnable(i)).start();
        }
    }
}

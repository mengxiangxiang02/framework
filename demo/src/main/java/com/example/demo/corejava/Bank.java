package com.example.demo.corejava;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    public Bank(int n,double inititalBalance)
    {
        accounts=new double[n];
        Arrays.fill(accounts,inititalBalance);
        bankLock=new ReentrantLock();
        sufficientFunds= bankLock.newCondition();
    }

    /**
     * Transfer money from one account to anoter
     * @param from  the account to transfer from
     * @param to    the account to transfer to
     * @param amount the amount to transfer
     * @throws InterruptedException
     */
    public void transfer(int from,int to ,double amount) throws InterruptedException {
        bankLock.lock();
        try
        {
            while (accounts[from]<amount)
            {
                sufficientFunds.await();//当前线程现在被阻塞了，并放弃了锁。一旦一个线程调用await方法，它进入该条件的等待集。当锁可用时，该线程不能马上解除阻塞。相反，它处于阻塞状态，直到另一个线程调用同一条件上的signalAll方法时为止。
            }
            System.out.println(Thread.currentThread());
            accounts[from]-=amount;
            System.out.printf("%10.2f from %d balance %10.2f to %d balance %10.2f  ",amount,from ,accounts[from],to,accounts[to]);
            accounts[to]+=amount;
            System.out.printf("  total Balance:%10.2f",getTotalBalance());
            sufficientFunds.signalAll();//注意调用signalAll不会立即激活一个等待线程。它仅仅解除等待线程的阻塞，以便这些线程可以在当前线程退出同步方法之后，通过竞争实现对对象的访问。
            //另一个方法signal，则是随机解除等待集中某个线程的阻塞状态。这比解除所有线程的阻塞更加有效，但也存在危险。
            //如果随机选择的线程发现自己仍然不能运行，那么它再次被阻塞。如果没有其他线程再次调用signal，那么系统就死锁了。
        }finally {
            bankLock.unlock();
        }
    }

    /**
     * gets the sum of all account balances
     * @return the total balance
     */
    public double getTotalBalance()
    {
        bankLock.lock();
        try
        {
            double sum=0;
            for(double a:accounts)
            {
                sum+=a;
            }
            return sum;
        }finally {
            bankLock.unlock();
        }
    }

    /**
     * gets the number of accounts in the bank
     * @return the number of accounts
     */
    public int size()
    {
        return accounts.length;
    }

}

package com.architecture.queue;

/**
 * @author : mengxiangxiang
 * @Date :   2019/1/14
 * @description :定义一个循环队列
 */
class Queue{
    private int maxSize;
    private long[] queArray;
    private int front;
    public int rear;
    private int nitems;
    public Queue(int s)
    {
        maxSize=s;
        queArray=new long[maxSize];
        front=0;
        rear=-1;
        nitems=0;
    }

    public void insert(long j){
        if(nitems==maxSize) return ;
        if(rear==maxSize-1) rear=-1;//循环队列
        queArray[++rear]=j;
        nitems++;
    }

    public long remove()
    {
        if(nitems==0) return 0;
        long temp=queArray[front++];

        if(front==maxSize) front=0;

        nitems--;

        return temp;
    }

    public long peekFront()
    {
        return queArray[front];
    }

    public boolean isEmpty()
    {
        return nitems==0;
    }

    public boolean isFull()
    {
        return nitems==maxSize;
    }

    public int size()
    {
        return nitems;
    }
}

public class QueueApp {
    public static void main(String args[])
    {
        Queue queue=new Queue(5);
        queue.insert(10);
        queue.insert(20);
        queue.insert(30);
        queue.insert(40);
        queue.insert(50);
        System.out.println(queue.rear);
        queue.insert(60);
        System.out.println(queue.rear);

    }
}

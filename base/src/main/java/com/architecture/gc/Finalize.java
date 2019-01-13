package com.architecture.gc;

public class Finalize {
    public static  Finalize finalize=null;
    public void isAlive()
    {
        System.out.println("Yes,I'm alive");
    }
    @Override
    public void finalize() throws Throwable {
        super.finalize();
        Finalize.finalize=this;
    }
    public static  void main(String args[]) throws InterruptedException {
        finalize=new Finalize();
        finalize=null;
        System.gc();
        Thread.sleep(500);
        if(finalize!=null)
        {
            finalize.isAlive();
        }else
        {
            System.out.println("yes i'm dead");
        }
        finalize=null;
        System.gc();
        Thread.sleep(500);
        if(finalize!=null)
        {
            finalize.isAlive();
        }else
        {
            System.out.println("yes i'm dead");
        }

    }
}

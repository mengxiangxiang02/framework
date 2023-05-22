package com.example.demo.corejava;

import java.time.LocalDate;

public class PairTest {
    public static <T extends Comparable> Pair<T> minmax(T[] a)
    {
        if(a==null||a.length==0)
        {
            return null;
        }
        T min=a[0];
        T max=a[0];
        for(int i=0;i<a.length;i++)
        {
            if(min.compareTo(a[i])>0){
                min=a[i];
            }
            if(max.compareTo(a[i])<0)
            {
                max=a[i];
            }
        }
        return new Pair<>(min,max);
    }
    public static void  main(String args[])
    {
        LocalDate[] birthdays={
                LocalDate.of(1906,12,9),
                LocalDate.of(1966,11,6),
                LocalDate.of(1968,10,1),
                LocalDate.of(2012,9,11),
        };
        Pair<LocalDate>     mm=minmax(birthdays);
        System.out.println("min date="+mm.getFirst());
        System.out.println("max date="+mm.getSecond());
    }


}

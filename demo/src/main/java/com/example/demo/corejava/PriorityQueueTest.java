package com.example.demo.corejava;

import java.time.LocalDate;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args)
    {
        PriorityQueue<LocalDate> priorityQueue=new PriorityQueue<>();
        priorityQueue.add(LocalDate.of(2013,02,12));
        priorityQueue.add(LocalDate.of(2012,02,12));
        priorityQueue.add(LocalDate.of(2015,02,12));
        priorityQueue.add(LocalDate.of(2014,02,12));
        System.out.println("iterator over elements");
        for(LocalDate localDate:priorityQueue)
        {
            System.out.println(localDate);
        }
        System.out.println("removing elements");
        while(!priorityQueue.isEmpty())
        {
            System.out.println(priorityQueue.remove());
        }
    }
}

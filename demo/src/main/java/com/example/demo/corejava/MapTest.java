package com.example.demo.corejava;

import java.util.*;

public class MapTest {
    public static void main(String[] args)
    {
        Map<String,Employee> staff=new HashMap<String,Employee>();
        staff.put("1990-10",new Employee(12.1,"mxx1"));
        staff.put("1991-10",new Employee(12.1,"mxx2"));
        staff.put("1993-10",new Employee(12.1,"mxx3"));
        staff.put("1985-10",new Employee(12.1,"mxx4"));
        System.out.println("print all staff");
        Set<Map.Entry<String, Employee>> entries = staff.entrySet();
        for(Map.Entry<String, Employee> entry:entries)//iterator
        {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        Iterator<Map.Entry<String, Employee>> iterator = entries.iterator();
        while(iterator.hasNext())
        {
            Map.Entry<String, Employee> next = iterator.next();
            System.out.println(next.getKey()+"vale"+next.getValue());
        }

        System.out.println("for each ");
        staff.forEach((K,V)->{
            System.out.println(K);
            System.out.println(V);
        });

        staff.remove("1990-10");//remove element



        Map<String,Employee> map=new LinkedHashMap<>();
        map.put("first",new Employee("first"));
        map.put("second",new Employee("second"));
        map.put("third",new Employee("third"));
        Iterator<Map.Entry<String, Employee>> iteratorMap = map.entrySet().iterator();
        Iterator<String> iteratorKey = map.keySet().iterator();
        Collection<Employee> values = map.values();
        for(Employee employee:values)
        {
            System.out.println(employee);
        }

        while(iteratorMap.hasNext())
        {
            Map.Entry<String, Employee> next = iteratorMap.next();
            System.out.println(next.getKey()+next.getValue());
        }

        while (iteratorKey.hasNext())
        {
            String next = iteratorKey.next();
            System.out.println(next);
        }

    }
}

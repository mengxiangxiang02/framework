package com.example.demo.corejava;

import java.util.Arrays;
import java.util.Comparator;

public class EmployeeCompareTest {
    public static void main(String args[])
    {
        Employee[] staff=new Employee[3];
        staff[0]=new Employee(12.2,"mxx");
        staff[1]=new Employee(13.2,"mxxx");
        staff[2]=new Employee(14.2,"mxxxx");
        Arrays.sort(staff);
        for(Employee e:staff)
        {
            System.out.println("name="+e.getName()+";salary="+e.getSalary());
        }

        Comparator<? super Employee> nameComparator=new EmployeeCompareTest().new nameComparator();
        Arrays.sort(staff,nameComparator);
        for(Employee e:staff)
        {
            System.out.println("name="+e.getName()+";salary="+e.getSalary());
        }

        Arrays.sort(staff,Comparator.comparing(employee -> employee.getName()));


    }


    class nameComparator implements Comparator<Employee>
    {

        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}

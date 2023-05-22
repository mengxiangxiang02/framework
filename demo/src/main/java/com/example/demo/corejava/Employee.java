package com.example.demo.corejava;

public class Employee implements Comparable<Employee>{
    private Double salary;
    private String name;
    public Employee(Double salary, String name) {
        this.salary = salary;
        this.name = name;
    }

    public Employee(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(salary,o.salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", name='" + name + '\'' +
                '}';
    }
}

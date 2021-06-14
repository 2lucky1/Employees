package com.muntian.entities;

public class Manager extends Employee {

    public Manager (long id, String name, int age, double salary, String gender){
        super(id, name, age, salary, gender);
    }

    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return super.toString() + ", position=Manager";
    }
}

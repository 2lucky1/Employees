package com.muntian.entities;

public class Designer extends Employee {


    private double rate;
    private int workedDays;

    public Designer(long id, String name, int age, double salary, String gender, double rate, int workedDays) {
        super(id, name, age, salary, gender);
        this.rate = rate;
        this.workedDays = workedDays;
    }

    @Override
    public double calculateSalary() {
        return salary + rate * workedDays;
    }

    @Override
    public String toString() {
        return super.toString() + ", position=Designer" +
                ", rate=" + rate +
                ", workedDays=" + workedDays;
    }
}

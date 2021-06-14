package com.muntian.entities;

import java.util.Random;

public class Developer extends Employee {

    private int fixedBugs;

    public Developer(long id, String name, int age, double salary, String gender, int fixedBugs){
        super(id, name, age, salary, gender);
        this.fixedBugs = fixedBugs;
    }

    @Override
    public double calculateSalary() {
        Random random = new Random();
        return (getSalary() + fixedBugs * 1.2) * (random.nextBoolean() ? 2 : 0);
    }

    @Override
    public String toString() {
        return super.toString() +", position=Developer" +
                ", fixedBugs=" + fixedBugs;
    }
}

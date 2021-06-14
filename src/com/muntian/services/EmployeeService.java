package com.muntian.services;

import com.muntian.entities.Employee;

public interface EmployeeService {

    void generateEmployees(int size);

    Employee[] getEmployees();

    void printEmployees(Employee[] array);

    double calculateSalaryAndBonus();

    Employee getById(long id);

    Employee getByName(String name);

    Employee[] sortByName();

    Employee[] sortByNameAndSalary();

    Employee edit(Employee employee);

    Employee remove(long id);
}

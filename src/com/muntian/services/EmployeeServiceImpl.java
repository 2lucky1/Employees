package com.muntian.services;

import com.muntian.entities.Designer;
import com.muntian.entities.Developer;
import com.muntian.entities.Employee;
import com.muntian.entities.Manager;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class EmployeeServiceImpl implements EmployeeService {

    public static final int MAX_AGE = 200;
    public static final double MAX_DEV_SALARY = 4000;
    public static final double MAX_MANAGER_SALARY = 7000;
    public static final double MAX_DESIGNER_SALARY = 2000;
    public static final double DESIGNER_RATE = 40;
    public static final int MAX_FIXED_BUGS = 20;
    public static final int MAX_WORKED_DAYS = 31;
    public static final int MAX_VALUE_OF_ID = 10_000;

    private Employee[] employees;
    private static final int DEVELOPER = 0;
    private static final int MANAGER = 1;
    private static final int DESIGNER = 2;

    private final Map<String, String> names = Map.of(
            "Vasya", "Male",
            "Petya", "Male",
            "Tolya", "Male",
            "Marina", "Female",
            "Alina", "Female",
            "Kolya", "Male",
            "Afanasii", "Male"
    );

    private final Set<Long> existingIds = new HashSet<>();

    @Override
    public void generateEmployees(int size) {
        Random random = new Random();
        employees = new Employee[size];
        for (int i = 0; i < size; i++) {
            int jobTitle = random.nextInt(3);

            long id;
            while (true) {
                id = random.nextInt(MAX_VALUE_OF_ID);
                if (!existingIds.contains(id)) {
                    existingIds.add(id);
                    break;
                }
            }
            String name = getRandomName();
            String gender = names.get(name);

            int age = random.nextInt(MAX_AGE);

            int workedDays = random.nextInt(MAX_WORKED_DAYS);

            switch (jobTitle) {
                case DEVELOPER:
                    int fixedBugs = random.nextInt(MAX_FIXED_BUGS);
                    Developer developer = new Developer(id, name, age, MAX_DEV_SALARY, gender, fixedBugs);
                    employees[i] = developer;
                    break;
                case MANAGER:
                    Manager manager = new Manager(id, name, age, MAX_MANAGER_SALARY, gender);
                    employees[i] = manager;
                    break;
                case DESIGNER:
                    Designer designer = new Designer(id, name, age, MAX_DESIGNER_SALARY, gender, DESIGNER_RATE, workedDays);
                    employees[i] = designer;
                    break;
            }
        }
    }

    @Override
    public Employee[] getEmployees() {
        return employees;
    }

    @Override
    public void printEmployees(Employee[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(i + 1 + ") " + array[i].toString());
        }
    }

    @Override
    public double calculateSalaryAndBonus() {
        double mSalary = 0.0;
        for (Employee employee : employees) {
            mSalary += employee.calculateSalary();
        }
        return mSalary;
    }

    @Override
    public Employee getById(long id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public Employee getByName(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public Employee[] sortByName() {
        Employee[] sortedArray = new Employee[employees.length];
        System.arraycopy(employees, 0, sortedArray, 0, sortedArray.length);
        for (int j = sortedArray.length - 1; j >= 1; j--) {
            for (int i = 0; i < j; i++) {
                String firstName = sortedArray[i].getName();
                String secondName = sortedArray[i + 1].getName();

                if (String.CASE_INSENSITIVE_ORDER.compare(firstName, secondName) > 0) {
                    Employee temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i + 1];
                    sortedArray[i + 1] = temp;
                }
            }
        }
        return sortedArray;
    }

    @Override
    public Employee[] sortByNameAndSalary() {
        Employee[] sortedArray = sortByName();
        for (int j = sortedArray.length - 1; j >= 1; j--) {
            for (int i = 0; i < j; i++) {
                String name1 = sortedArray[i].getName();
                String name2 = sortedArray[i + 1].getName();
                double salary1 = sortedArray[i].getSalary();
                double salary2 = sortedArray[i + 1].getSalary();
                if (name1.equals(name2) && salary1 > salary2) {
                    Employee temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i + 1];
                    sortedArray[i + 1] = temp;
                }
            }
        }
        return sortedArray;
    }

    @Override
    public Employee edit(Employee employee) {
        for (int i = 0; i < employees.length; i++) {
            Employee substitutedEmployee;

            if (employees[i].getId() == employee.getId()) {
                substitutedEmployee = employees[i];
                employee.setId(employees[i].getId());
                employees[i] = employee;
                return substitutedEmployee;
            }
        }
        return null;
    }

    @Override
    public Employee remove(long id) {
        Employee[] newArray = new Employee[employees.length - 1];
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                Employee removedEmployee = employees[i];
                System.arraycopy(employees, 0, newArray, 0, i);
                if (i != employees.length - 1) {
                    System.arraycopy(employees, i + 1, newArray, i, newArray.length - i);
                }
                employees = newArray;
                existingIds.remove(id);
                return removedEmployee;
            }
        }
        return null;
    }

    public Set<Long> getExistingIds() {
        return existingIds;
    }

    private String getRandomName() {
        Random random = new Random();
        return (String) names.keySet().toArray()[random.nextInt(names.size())];
    }

}

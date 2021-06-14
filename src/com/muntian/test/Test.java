package com.muntian.test;

import com.muntian.entities.Developer;
import com.muntian.entities.Employee;
import com.muntian.services.EmployeeServiceImpl;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        EmployeeServiceImpl service = new EmployeeServiceImpl();

        service.generateEmployees(7);
        service.printEmployees(service.getEmployees());
        System.out.println();

        System.out.println("Total salary amount = " + service.calculateSalaryAndBonus());
        System.out.println();

        System.out.println("Sorted by name:");
        service.printEmployees(service.sortByName());
        System.out.println();

        System.out.println("Sorted by name and salary:");
        service.printEmployees(service.sortByNameAndSalary());
        System.out.println();

        System.out.println("Remove by ID:");
        long id = getRandomId(service);
        System.out.println("random id = " + id);
        System.out.println("Removed employee = " + service.remove(id));
        System.out.println();

        System.out.println("Employees list after removing:");
        service.printEmployees(service.getEmployees());
        System.out.println();

        System.out.println("Get employee by id:");
        long id2 = getRandomId(service);
        System.out.println(service.getById(id2));
        System.out.println();

        System.out.println("Get employee by name:");
        String name = service.getEmployees()[service.getEmployees().length - 1].getName();
        System.out.println(service.getByName(name));
        System.out.println();

        long randomIdFromExisting = getRandomId(service);
        Employee testEmployee = new Developer(randomIdFromExisting, "TestEmployee",
                                        154, 10000, "Male", 21);
        System.out.println();
        System.out.println("Random id = " + randomIdFromExisting);

        System.out.println("Employee data by random id:");
        System.out.println(service.getById(randomIdFromExisting));
        System.out.println("Test employee:");
        System.out.println(testEmployee);
        System.out.println("Edit method returns:");
        System.out.println(service.edit(testEmployee));
        System.out.println();

        System.out.println("Print all employees:");
        service.printEmployees(service.getEmployees());
    }

    private static long getRandomId(EmployeeServiceImpl service){
        Long[] existingIds = new Long[service.getExistingIds().size()];
                service.getExistingIds().toArray(existingIds);
        Random random = new Random();
        return existingIds[random.nextInt(existingIds.length)];

    }
}

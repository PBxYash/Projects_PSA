package com.example.demo4;

import java.util.*;
import java.util.stream.Collectors;

public class MainUtil {
    public static void main(String[] args) {
        List<Employee> data = Arrays.asList(
                new Employee("mike",1000),
                new Employee("stallin",2000),
                new Employee("Amul",1000)
        );
        Map<Double, List<Employee>> collect = data.stream().collect(Collectors.groupingBy(Employee::getSalary));

        for (Map.Entry<Double,List<Employee>>entry :collect.entrySet()) {
            double Salary=entry.getKey();
            List<Employee> employeeList = entry.getValue();
            System.out.println("Employee salary is "+Salary);
            for (Employee employee:employeeList) {
                System.out.println("\t"+employee.getName());
            }
        }

    }
}

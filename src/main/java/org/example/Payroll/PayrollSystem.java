package org.example.Payroll;

import org.example.Payroll.Configuration.SpringConfig;


import org.example.Payroll.Service.EmployeeServiceImpl;
import org.example.Payroll.controller.EmployeeController;
import org.example.Payroll.model.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class PayrollSystem {
    private static Boolean bool = true;
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws SQLException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );



        EmployeeServiceImpl employeeService = context.getBean("employeeServiceImpl", EmployeeServiceImpl.class);


        EmployeeController employeeController = context.getBean("employeeController", EmployeeController.class);





        while (bool){
            System.out.println("1. To check salary");
            System.out.println("2. To add 10% to Salaried-Commission Employee");
            System.out.println("any. To Quit");
            String choice = read.readLine();

            switch (choice){
                case "1":
                    for(Employee employee : employeeController.getAll())
                        System.out.println(employee.getId() + "," + employee.getType() + "," + employee.getSalary());

                    break;
                case "2":
                    System.out.println("Please Enter type of Employee");
                    String t = read.readLine();
                    employeeController.update(employeeService.getOne(t).getId(), employeeService.getOne(t).getSalary() * 1.1, employeeService.getOne(t).getType());
                    break;
                default:
                    bool = false;

            }
        }






    }
}

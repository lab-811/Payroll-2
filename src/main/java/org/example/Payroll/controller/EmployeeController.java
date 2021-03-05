package org.example.Payroll.controller;

import org.example.Payroll.Service.EmployeeServiceImpl;
import org.example.Payroll.event.SalaryChangeEvent;
import org.example.Payroll.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmployeeController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;


    private EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    public Employee getOne(String type){
        return employeeService.getOne(type);
    }

    public void update(Long id, double salary, String type){

        employeeService.updateEmployee(id, salary, type);

        this.eventPublisher.publishEvent(new SalaryChangeEvent(this, employeeService.getOne(type)));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}

package org.example.Payroll.Service;

import org.example.Payroll.event.SalaryChangeEvent;
import org.example.Payroll.model.Employee;
import org.example.Payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {



    @Autowired
    private EmployeeRepository employeeRepository;



    public List<Employee> getAll() {
        List<Employee> listOfEmployee = new ArrayList<>();
        employeeRepository.findAll()
                .forEach(listOfEmployee::add);

        return listOfEmployee;
    }


    public Employee getOne(String type) {
        return  employeeRepository.findByType(type);
    }


    public void updateEmployee(Long id, double salary, String type) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            employee.setSalary(salary);

            employeeRepository.save(employee);

        }
    }


}

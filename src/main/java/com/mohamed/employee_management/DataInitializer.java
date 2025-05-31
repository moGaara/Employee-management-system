package com.mohamed.employee_management;


import com.mohamed.employee_management.dto.AddNewEmployeeRequest;
import com.mohamed.employee_management.module.Employee;
import com.mohamed.employee_management.repository.EmployeeRepository;
import com.mohamed.employee_management.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final EmployeeService employeeService;


    public DataInitializer(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) {
        employeeService.addNewEmployee(buildEmployee("Alice", "alice@example.com", "HR", "myPassword1"));
        employeeService.addNewEmployee(buildEmployee("Bob", "bob@example.com", "IT","myPassword2"));

        employeeService.addNewEmployee(buildEmployee( "Charlie", "charlie@example.com", "Sales","myPassword3"));
        employeeService.addNewEmployee(buildEmployee( "David", "david@example.com", "IT","myPassword4"));
        employeeService.addNewEmployee(buildEmployee( "Eva", "eva@example.com", "HR","myPassword5"));
    }

    private AddNewEmployeeRequest buildEmployee(String name, String email, String department, String password)
    {
        return AddNewEmployeeRequest.builder()
                .name(name)
                .email(email)
                .departmentName(department)
                .password(password)
                .build();
    }
}

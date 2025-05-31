package com.mohamed.employee_management.service.impl;


import com.mohamed.employee_management.dto.AddNewEmployeeRequest;
import com.mohamed.employee_management.dto.EmployeeDto;
import com.mohamed.employee_management.module.Employee;
import com.mohamed.employee_management.repository.EmployeeRepository;
import com.mohamed.employee_management.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService
{
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public EmployeeDto getEmployeeById(int employeeId)
    {
        return employeeRepository.findById(employeeId).map(this::convertToEmployeeDto)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));


    }


    @Override
    public void deleteEmployeeById(int employeeId)
    {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));


        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<EmployeeDto> getEmployeeByDepartment(String department)
    {

        if(!employeeRepository.existsByDepartmentName(department))
        {
            throw new EntityNotFoundException("Department does not exist: " + department);
        }



        List<Employee> employees = employeeRepository.findByDepartmentName(department);

        if (employees.isEmpty())
        {
            throw new IllegalArgumentException("No employees found in department: " + department);
        }



        return employees.stream()
                .map(this::convertToEmployeeDto)
                .toList();

    }

    @Override
    public EmployeeDto updateEmployeeById(int employeeId, EmployeeDto employeeDto)
    {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));


        if( !employeeDto.getEmail().equals(employee.getEmail()) && employeeRepository.existsByEmail(employeeDto.getEmail()))
        {
            throw new IllegalArgumentException("Email already exists");
        }


        employee.setEmail(employeeDto.getEmail());
        employee.setDepartmentName(employeeDto.getDepartmentName());
        employee.setName(employeeDto.getName());



        return convertToEmployeeDto(employeeRepository.save(employee));

    }


    @Override
    public Employee addNewEmployee(AddNewEmployeeRequest addNewEmployeeRequest)
    {
        if (employeeRepository.existsByEmail(addNewEmployeeRequest.getEmail()))
        {
            throw new IllegalArgumentException("Email already exists");
        }


        Employee newEmployee = Employee.builder()
                .name(addNewEmployeeRequest.getName())
                .email(addNewEmployeeRequest.getEmail())
                .departmentName(addNewEmployeeRequest.getDepartmentName())
                .password(passwordEncoder.encode(addNewEmployeeRequest.getPassword()))
                .build();


        return employeeRepository.save(newEmployee);

    }


    @Override
    public List<EmployeeDto> getAllEmployees(int page, int size)
    {

        if (page < 0)
        {
            throw new IllegalArgumentException("Page index must not be negative.");
        }


        if (size <= 0)
        {
            throw new IllegalArgumentException("Page size must be greater than zero.");
        }


        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> pagedEmployees = employeeRepository.findAll(pageable);



        if (page > pagedEmployees.getTotalPages())
        {
            throw new IllegalArgumentException("Requested page exceeds total number of pages.");
        }


        return pagedEmployees.getContent().stream()
                .map(this::convertToEmployeeDto)
                .toList();

    }

    private EmployeeDto convertToEmployeeDto(Employee employee)
    {
        EmployeeDto dto = new EmployeeDto();

        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartmentName(employee.getDepartmentName());


        return dto;
    }



}

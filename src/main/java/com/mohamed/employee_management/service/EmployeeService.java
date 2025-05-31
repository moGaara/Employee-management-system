package com.mohamed.employee_management.service;


import com.mohamed.employee_management.dto.AddNewEmployeeRequest;
import com.mohamed.employee_management.dto.EmployeeDto;
import com.mohamed.employee_management.module.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService
{
    public EmployeeDto getEmployeeById(int employeeId);
    public Employee addNewEmployee(AddNewEmployeeRequest addNewEmployeeRequest);

    public void deleteEmployeeById(int employeeId);

    public List<EmployeeDto> getEmployeeByDepartment(String department);

     public EmployeeDto updateEmployeeById(int employeeId, EmployeeDto employeeDto);
    public List<EmployeeDto>  getAllEmployees( int page, int size);

}

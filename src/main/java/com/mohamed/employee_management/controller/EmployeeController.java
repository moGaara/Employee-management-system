package com.mohamed.employee_management.controller;


import com.mohamed.employee_management.dto.AddNewEmployeeResponse;
import com.mohamed.employee_management.dto.AddNewEmployeeRequest;
import com.mohamed.employee_management.dto.EmployeeDto;
import com.mohamed.employee_management.module.Employee;
import com.mohamed.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor

public class EmployeeController
{
    private final EmployeeService employeeService;


    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable int employeeId) {
        EmployeeDto employee = employeeService.getEmployeeById(employeeId);



        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> delete(@PathVariable int employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<EmployeeDto>> getByDepartment(@PathVariable String department) {
        List<EmployeeDto> employees = employeeService.getEmployeeByDepartment(department);



        return ResponseEntity.ok(employees);
    }

    @GetMapping                                          // get all employees with pagination support
    public ResponseEntity<List<EmployeeDto>> getEmployees
            (
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size
            )
    {



        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees(page,size);


        return ResponseEntity.ok(employeeDtos);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int employeeId, @Valid @RequestBody EmployeeDto employeeDto)
    {

        EmployeeDto response = employeeService.updateEmployeeById(employeeId, employeeDto);


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping
    public ResponseEntity<AddNewEmployeeResponse> addNewEmployee(@Valid @RequestBody AddNewEmployeeRequest addNewEmployeeRequest)
    {
        Employee employee = employeeService.addNewEmployee(addNewEmployeeRequest);

        AddNewEmployeeResponse response = AddNewEmployeeResponse.builder()
                .id(employee.getEmployeeId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

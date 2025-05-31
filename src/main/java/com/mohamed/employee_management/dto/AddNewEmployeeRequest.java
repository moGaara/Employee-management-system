package com.mohamed.employee_management.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddNewEmployeeRequest implements Serializable
{

    @NotBlank(message = "Name of Employee cannot be blank.")
    private String name;

    @NotBlank(message = "Password cannot be blank.")
    private String password;

    @NotBlank(message = "Department name cannot be blank.")
    private String departmentName;

    @NotBlank(message = "Please enter a valid email.")
    @Email(message = "Invalid email format.")
    private String email;



}

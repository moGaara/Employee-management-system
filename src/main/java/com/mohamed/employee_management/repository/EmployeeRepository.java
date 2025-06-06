package com.mohamed.employee_management.repository;

import com.mohamed.employee_management.module.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{
    @Override
    Optional<Employee> findById(Integer integer);



    List<Employee> findByDepartmentName(String departmentName);

    boolean existsByDepartmentName(String departmentName);



    boolean existsByEmail(String email);





    @Override
    Page<Employee> findAll(Pageable pageable);

    void deleteById(Integer id);
}

package com.company.repository;

import com.company.entity.Employee;

import java.util.List;

public interface EmployeeRepo {
    void save(Employee employee);
    Employee findById(long id);
    List<Employee> findAll();
    Employee update(Employee employee);
    boolean delete(long id);
}

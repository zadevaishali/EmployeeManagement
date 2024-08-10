package com.empmgt.service;

import java.util.List;

import com.empmgt.entity.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
	List<Employee> getAllEmployee();
    Employee getEmployeeById (Long id);
    Employee updateEmployee(Employee employee,Long id);
    void deleteEmployee(Long id);
}

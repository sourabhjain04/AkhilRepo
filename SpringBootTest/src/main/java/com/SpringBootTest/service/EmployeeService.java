package com.SpringBootTest.service;

import java.util.List;
import java.util.Optional;

import com.SpringBootTest.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);
	
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(long id);
	
	
	
	
}

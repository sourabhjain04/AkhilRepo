package com.SpringBootTest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.SpringBootTest.exception.ResourceNotFoundException;
import com.SpringBootTest.model.Employee;
import com.SpringBootTest.repository.EmployeeRepository;

@Service
class EmployeeServcieImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeServcieImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {

        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee already exist with given email:" + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }


	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee"));
	}

	

}

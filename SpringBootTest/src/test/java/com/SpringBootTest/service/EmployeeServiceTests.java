package com.SpringBootTest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.BDDMockito.given;

import com.SpringBootTest.exception.ResourceNotFoundException;
import com.SpringBootTest.model.Employee;
import com.SpringBootTest.repository.EmployeeRepository;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
	
	@Mock 
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServcieImpl employeeService;
	
	
	

	
	
	@DisplayName("Junit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
		
		//given -precondition or setup
		Employee employee = Employee.builder()
				.id(1L)
				.firstName("John")
				.lastName("Doe")
				.email("John@gmail.com")
				.build();
		
		
		//when - action or the behaviour that we are going to test
		given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
		given(employeeRepository.save(employee)).willReturn(employee);
		System.out.println(employeeRepository);
		System.out.println(employeeService);
		
		Employee savedEmployee = employeeService.saveEmployee(employee);
		System.out.println(savedEmployee);
		//then - verify the output
		assertThat(savedEmployee).isNotNull();
			//assertThat(savedEmployee.getId()).isGreaterThan(0);
	}

	
	@DisplayName("JUnit test for saveEmployee method which throws exception")
	@Test
	void givenExistingEmail_whenSaveEmployee_thenThrowsException() {
	    // given
	    Employee employee = Employee.builder()
	            .id(1L)
	            .firstName("John")
	            .lastName("Doe")
	            .email("John@gmail.com")
	            .build();

	    given(employeeRepository.findByEmail(employee.getEmail()))
	            .willReturn(Optional.of(employee));

	    // when + then
	    ResourceNotFoundException ex = 
	            org.junit.jupiter.api.Assertions.assertThrows(
	                ResourceNotFoundException.class,
	                () -> employeeService.saveEmployee(employee));

	    // optional: assert message
	    org.assertj.core.api.Assertions.assertThat(ex.getMessage())
	            .contains("Employee already exist");

	    // and verify interactions
	    org.mockito.BDDMockito.then(employeeRepository)
	            .should().findByEmail(employee.getEmail());
	    org.mockito.BDDMockito.then(employeeRepository)
	            .should(org.mockito.Mockito.never()).save(org.mockito.Mockito.any(Employee.class));
	}



	// getEmployeeById
    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        // given
    	Employee employee = Employee.builder()
				.id(1L)
				.firstName("John")
				.lastName("Doe")
				.email("John@gmail.com")
				.build();
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        // when
		Employee savedEmployee = employeeService.getEmployeeById(employee.getId());
        // then
        assertThat(savedEmployee).isNotNull();

    }
    
    //getAllEmployees
    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
		// given
		Employee employee = Employee.builder()
				.id(1L)
				.firstName("John")
				.lastName("Doe")
				.email("john@gmail.com")	
				.build();
		given(employeeRepository.findAll()).willReturn(List.of(employee));
				// when
		List<Employee> employeesList = employeeService.getAllEmployees();
		// then
		assertThat(employeesList).isNotNull();
		assertThat(employeesList.size()).isEqualTo(1);
	}

	
	
	
	
}

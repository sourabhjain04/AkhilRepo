package com.SpringBootTest.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.mockito.BDDMockito.given;
import com.SpringBootTest.model.Employee;

@DataJpaTest
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	private Employee employee;
	
	@BeforeEach
	void setup() {
		employee =Employee.builder()
			.firstName("John")
			.lastName("Doe")
			.email("John@gmail.com")
			.build();
		
	}
	
	@DisplayName("Junit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){
		
		//given -precondition or setup
		
		
		
		//when - action or the behaviour that we are going to test
		Employee savedEmployee = employeeRepository.save(employee);
		
		//then - verify the output
		
		assertThat(savedEmployee).isNotNull();
			assertThat(savedEmployee.getId()).isGreaterThan(0);
	}
	
	@Test
	@DisplayName("Junit test for get all employees operation")
	public void givenEmployeesList_whenFindAll_thenEmployeesList() {
		
		//given -precondition or setup
		
		
		
		Employee employee1 =Employee.builder()
				.firstName("Vedika")
				.lastName("jain")
				.email("vedika@gmail.com")
				.build();
		employeeRepository.save(employee);
		employeeRepository.save(employee1);
		//when - action or the behaviour that we are going to test
		List<Employee> employeeList = employeeRepository.findAll();
		//then - verify the output
		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(2);
		
		
		
	}
	@Test
	@DisplayName("Junit test for get employee by id operation")
	public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
		//givem -precondition or setup
		
		employeeRepository.save(employee);
		
		//when - action or the behaviour that we are going to test
		Employee employeeDB = employeeRepository.findById(employee.getId()).get();
		//then - verify the output
		assertThat(employeeDB).isNotNull();
	}
	
}

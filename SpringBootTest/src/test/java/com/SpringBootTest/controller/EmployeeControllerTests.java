package com.SpringBootTest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.hamcrest.CoreMatchers;
import com.SpringBootTest.model.Employee;
import com.SpringBootTest.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class EmployeeControllerTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private EmployeeService employeeService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws
	Exception{
		// given - precondition or setup
		Employee employee =Employee.builder()
				.firstName("John")
				.lastName("Doe")
				.email("John@gmail.com")
				.build();
		
		BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class)))
		.willAnswer((invocation)-> invocation.getArgument(0));
		
		// when - action or the behaviour that we are going to test
		ResultActions response = mockMvc.perform(post("/api/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee)));
		
		// then - verify the output
		response.andDo(print()).andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
				Matchers.equalTo(employee.getFirstName())))
		.andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
				Matchers.equalTo(employee.getLastName())))
		.andExpect(MockMvcResultMatchers.jsonPath("$.email",
				Matchers.equalTo(employee.getEmail())));
	}
	
	//get all employees
	@Test
	void givenListofEmplyees_whenGetAllEmployees_ThenReturnEmployeeList() throws
	Exception{
		// given - precondition or setup
		List<Employee> listofEmployees = new ArrayList<>();
		listofEmployees.add(Employee.builder().firstName("john").lastName("Deo").email("John@gmail.com").build());
		
		listofEmployees.add(Employee.builder().firstName("marry").lastName("Deo").email("marry@gmail.com").build());
		BDDMockito.given(employeeService.getAllEmployees()).willReturn(listofEmployees);
		//
		// when - action or the behaviour that we are going to test
		ResultActions response = mockMvc.perform(get("/api/employees"));
		
		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()",is(listofEmployees.size())));
				
	
	}
	
}

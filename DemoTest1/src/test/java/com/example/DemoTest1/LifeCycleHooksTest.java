package com.example.DemoTest1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LifeCycleHooksTest {

	
	private Calculator calc;
	
	@BeforeAll
	static void initAll() {
		System.out.println("Before all tests");
	}
	
	@AfterAll
	static void tearDownAll() {
		System.out.println("After all tests");
	}
	
	@BeforeEach
	void init() {
		calc = new Calculator();
		System.out.println("Before each test");
	}
	@AfterEach
	void cleanup() {
		calc = null;
		System.out.println("After each test");
	}
	
	
	@Test
	@Order(1)
	void addition_isIsolatedPerTest() {
		int sum = calc.add(1, 2);
		assertEquals(3, sum);
		
	}
	
	@Test
	@Disabled("this should be skipped")
	@Order(2)
	void substraction_isIsolatedPerTest() {
	int diff = calc.subtract(5, 3);
	assertEquals(2, diff);
	}
	
}

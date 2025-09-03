package com.example.DemoTest1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAnnotationBasicsTest {

	@Test 
	@Order(2)
	void add_shouldReturnSum() {
		System.out.println("Running add_shouldReturnSum test");
		Calculator calc = new Calculator();
		int result = calc.add(2, 3);
		assertEquals(5, result);
	}
	@Test 
	@Order(1)
	void subtract_shouldReturnDifference() {
		System.out.println("Running subtract_shouldReturnDifference test");
		Calculator calc = new Calculator();
		int result = calc.subtract(4, 3);
		assertEquals(1, result);
	}
}

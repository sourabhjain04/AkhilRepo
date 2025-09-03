package com.example.DemoTest1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CalculatorNestedTest {

	
	Calculator calc;
	@BeforeEach
	void init() {
		calc = new Calculator();
	}
	
	@Nested
	@DisplayName("Addition Tests")
	class AdditionTests {
		@Test
		void testPositiveNumbers() {
			assertEquals(7,calc.add(3, 4));
			
		}
		
		
		@Test
		void testNegativeNumbers() {
			assertEquals(-7,calc.add(-3, -4));
			
		}
	}
	
	@Nested
	@DisplayName("Division Tests")
	class DivisionTests {
		@Test
		void testDivisionByNonZero() {
			assertEquals(2,calc.divide(8, 4));
			
		}
		
		@Test
		void testDivisionByZero() {
			assertThrows(ArithmeticException.class, () -> {
				calc.divide(8, 0);
			});
			
		}
	}
}

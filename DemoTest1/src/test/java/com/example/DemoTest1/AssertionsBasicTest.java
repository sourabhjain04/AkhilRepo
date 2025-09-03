package com.example.DemoTest1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AssertionsBasicTest {
	@Test
	void AssertEquals_example() {
		Calculator calc = new Calculator();
		assertEquals(9, calc.multiply(3, 3), "3*3 must be 9");
	}
	@Test
	void assertTrue_example() {
		Calculator calc = new Calculator();
		assertTrue(calc.isEven(4), "4 is even number");
		assertTrue(!calc.isEven(5), "5 is odd number");
		
	}
	
	@Test
	void assertThrows_example() {
		Calculator calc = new Calculator();
	IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> 
			calc.divide(5, 0),"division by zero should throw exception");
	assertEquals("Division by zero is not allowed.", ex.getMessage(),"Exception message should match"
		);
	}
	
	
	
	
}

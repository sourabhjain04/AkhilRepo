package com.example.DemoTest1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.RepeatedTest;

public class RepeatedTestExample {

	
	
	@RepeatedTest(100)
	void testIsEvenMultipleTimes() {
	
		Calculator calc = new Calculator();
		Calculator calc1 = new Calculator();
		assertTrue(calc.isEven(2), 2 + " should be even");
		
		
		
	}
}

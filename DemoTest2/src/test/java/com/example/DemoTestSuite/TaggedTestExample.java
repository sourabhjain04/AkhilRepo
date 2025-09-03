package com.example.DemoTestSuite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.example.DemoTest2.Calculator;

public class TaggedTestExample {

	@Test
	@Tag("fast")
	void testAdditionFast() {
		Calculator calc = new Calculator();
		assertEquals(2, calc.add(1, 1));
	}
	
	@Test
	@Tag("slow")
	void testAdditionSlow() {
		Calculator calc = new Calculator();
		assertEquals(5, calc.add(2, 3));
	}
}

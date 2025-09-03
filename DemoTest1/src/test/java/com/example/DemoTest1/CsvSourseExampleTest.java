package com.example.DemoTest1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CsvSourseExampleTest {
	

	@ParameterizedTest
	@CsvSource({
		"1, 1, 2",
		"2, 3, 5",
		"-4, 6, 2",
		"10, 15, 25",
		"20, 30, 50"
	})
	void testAddition(int a, int b, int expected) {
	Calculator calc = new Calculator();
	assertEquals(expected, calc.add(a, b),()->a +" + "+b+" should equal "+expected);
		
	}

}

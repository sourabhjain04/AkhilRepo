package com.example.DemoTest1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValueSourceExampleTest {

	@ParameterizedTest
	@ValueSource(ints = {2, 4, 6, 8, 10})
	void testIsEven(int number) {
		Calculator calc = new Calculator();
		assertTrue(calc.isEven(number), number + " should be even");
	}
}

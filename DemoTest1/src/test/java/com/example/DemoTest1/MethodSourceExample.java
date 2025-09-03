package com.example.DemoTest1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class MethodSourceExample {

	static Stream<org.junit.jupiter.params.provider.Arguments> additionProvider() {
		return Stream.of(
			org.junit.jupiter.params.provider.Arguments.of(1, 1, 2),
			org.junit.jupiter.params.provider.Arguments.of(2, 3, 5),
			org.junit.jupiter.params.provider.Arguments.of(-4, 6, 2),
			org.junit.jupiter.params.provider.Arguments.of(10, 15, 25),
			org.junit.jupiter.params.provider.Arguments.of(20, 30, 50)
		);
	}
	@ParameterizedTest
	@MethodSource("additionProvider")
	void testAddition(int a, int b, int expected) {
		Calculator calc = new Calculator();
		assertEquals(expected, calc.add(a, b),()->a +" + "+b+" should equal "+expected);
			
	}
	
}

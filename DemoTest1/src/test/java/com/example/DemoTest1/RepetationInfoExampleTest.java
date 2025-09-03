package com.example.DemoTest1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class RepetationInfoExampleTest {
	
	@RepeatedTest(5)
	void testAdditionRepeated(RepetitionInfo repetitionInfo) {
		
		
		Calculator calc = new Calculator();
		int result = calc.add(2, 3);
		System.out.println("Runnig Repetition"+repetitionInfo.getCurrentRepetition()+" of "+repetitionInfo.getTotalRepetitions());
		assertEquals(5, result);
		
	}
}

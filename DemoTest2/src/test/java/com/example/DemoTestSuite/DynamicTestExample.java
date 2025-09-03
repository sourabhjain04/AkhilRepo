package com.example.DemoTestSuite;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class DynamicTestExample {
	@TestFactory
	Stream<DynamicTest> testEvenNumbers(){
		
		
		return IntStream.range(1,6).mapToObj(n->DynamicTest.dynamicTest("Test Even Number "+n,()->{
			if(n%2!=0) {
				throw new Exception(n+" is not even number");
			}
		}));
		
	}

}

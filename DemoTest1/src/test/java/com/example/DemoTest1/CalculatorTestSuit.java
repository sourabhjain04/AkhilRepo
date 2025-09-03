package com.example.DemoTest1;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
/*
 * @SelectClasses({ CalculatorNestedTest.class, AssertionsBasicTest.class, })
 */
@SelectPackages("com.example.DemoTest1")
public class CalculatorTestSuit {

}

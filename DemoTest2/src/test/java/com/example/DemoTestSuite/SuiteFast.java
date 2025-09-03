package com.example.DemoTestSuite;

import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(TaggedTestExample.class)

@IncludeTags("fast")
@ExcludeTags("slow")
public class SuiteFast {

}

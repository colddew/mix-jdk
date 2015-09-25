package edu.ustc.mix.miscellaneous.annotation;

import java.lang.annotation.Repeatable;

// 可重复的注解
@Repeatable(TestCases.class)
public @interface TestCase {
	String params();
	String expected();
}

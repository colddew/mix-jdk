package edu.ustc.mix.miscellaneous.annotation;

public class Factorial {
	
	@TestCase(params = "4", expected = "24")
	@TestCase(params = "0", expected = "1")
	public static long factorial(int n) {
		return n <= 1 ? 1 : n * factorial(n - 1);
	}
}

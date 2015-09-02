package edu.ustc.mix.lambda;

/**
 * 多个接口中有同名方法必须在实现类中解决冲突，类中方法优先于接口中的同名方法
 */
interface Person {
	
	long getId();
	
	default String getName() {
		return "John Q. Public";
	}
}

interface Persistent {
	default String getName() {
		return getClass().getName() + "_" + hashCode();
	}
}

class Student implements Person, Persistent {
	
	public long getId() {
		return 42;
	}
	
	public String getName() {
		return Person.super.getName();
	}
}

package edu.ustc.mix.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Person {
	@Override
	public String toString() {
		return "person";
	}
}

class Employee extends Person {
	@Override
	public String toString() {
		return "employee";
	}
}

public class Generics {
	
	// 协变covariant, 可以接受子类型
	public static List<? extends Person> read() {
		
		List<Employee> list = new ArrayList<Employee>();
		Employee employee = new Employee();
		list.add(employee);
		
		return list;
	}
	
	// 逆变contravariant, 可以接受父类型
	public static void write(List<? super Employee> list) {
		System.out.println("write result: " + list);
	}
	
	// 函数类型的参数是逆变的，函数类型的返回值是协变的
	// 实现范型lambda表达式，参数类型加super，返回值类型加extends
	public static <T> void functionalProgram(Supplier<? extends T> first, 
		Consumer<? super T> second, Consumer<? super Throwable> handler) {
		
	}
	
	public static void main(String[] args) {
		
		List<? extends Person> read = read();
		System.out.println("read result: " + read);
		
		List<Person> list = new ArrayList<Person>();
		Person person = new Person();
		list.add(person);
		write(list);
	}
}

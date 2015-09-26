package edu.ustc.mix.jdk7.special;

import java.util.Objects;

class Person {
	
	private String first;
	private String last;
	
	public String getFirst() {
		return first;
	}
	
	public void setFirst(String first) {
		this.first = first;
	}
	
	public String getLast() {
		return last;
	}
	
	public void setLast(String last) {
		this.last = last;
	}
	
	public boolean equals(Object otherObject) {
		
		if (this == otherObject) {
			return true;
		}
		
		if (otherObject == null) {
			return false;
		}
		
		if (getClass() != otherObject.getClass()) {
			return false;
		}
		
		Person other = (Person) otherObject;
		
		return Objects.equals(first, other.first) && Objects.equals(last, other.last);
	}
	
	public int hashCode() {
		return Objects.hash(first, last);
	}
}

class Point {
	
	private int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int compareTo(Point other) {
		
		int diff = Integer.compare(x, other.x); // No risk of overflow
		if (diff != 0) {
			return diff;
		}
		
		return Integer.compare(y, other.y);
	}
}

public class ObjectOperation {
	
	public static void main(String[] args) {
		
		Person p1 = new Person();
		p1.setFirst("a");
		p1.setLast("b");
		
		Person p2 = new Person();
		p2.setFirst("a");
		p2.setLast("b");
		
		System.out.println(p1.equals(p2));
		
		Person p3 = null;
		System.out.println(Objects.toString(p3));
		System.out.println(Objects.toString(p3, ""));
		
		Point point1 = new Point(0, 0);
		Point point2 = new Point(-1, -1);
		System.out.println(point2.compareTo(point1));
	}
}

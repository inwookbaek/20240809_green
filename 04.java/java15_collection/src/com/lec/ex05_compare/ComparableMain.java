package com.lec.ex05_compare;

import java.util.Iterator;
import java.util.TreeSet;

/*
	Comparable vs Comparator
	
	TreeSet객체와 TreeMap의 키는 저장과 동시에 자동으로 오름차순으로 정렬되는데 숫자(Integer, Double..)
	타입일 경우에는 값을 정렬하고, 문자열(String)타입일 경우에는 유니코드로 정렬된다.
	
	TreeSet객체와 TreeMap을 정렬하기 위해서는 java.lang.Comparable인터페이스를 구현한 객체를 요구하는데
	Integer, Double, String등으로 모두 Comparable인터페이스를 구현하고 있다.
	
	사용자가 정의한 클래스를 TreeSet이나 TreeMap에 저장할 경우에 Comparable을 구현하고 있지 않을 경우에
	자동정렬을 할 수가 없다. Comparable인터페이스는 compareTo() 추상메서드가 정의되어 있기 때문에 사용자
	가 정의하는 클래스도 Comparable을 구현하려면 compareTo()메서드를 재정의해야 한다.
	
	compareTo()메서드는 int값을 리턴하는데 객체가 동일할 경우는 0, 작을 경우는 -1, 클 경우는 1을 리턴한
	다. TreeSet객체와 TreeMap의 키가 Comparable을 구현하고 있지 않을 경우에는 객체를 저장하는 순간 Class
	CastException예외가 발생한다.
*/
public class ComparableMain {

	public static void main(String[] args) {
		
		TreeSet<String> set1= new TreeSet<String>();
		set1.add("소향");
		set1.add("김민재");
		
		TreeSet<Student> students = new TreeSet<Student>();
		students.add(new Student(10, "홍길동"));
		students.add(new Student(2, "홍길순"));
		students.add(new Student(3, "홍길자"));
		students.add(new Student(4, "홍길동"));
		students.add(new Student(5, "손흥민"));
		students.add(new Student(6, "이강인"));
		students.add(new Student(7, "김민재"));
		students.add(new Student(8, "나얼"));
		students.add(new Student(9, "소향"));
		System.out.println("students의 총 갯수 = " + students.size());
		System.out.println();
		
		Iterator<Student> student = students.iterator();
		while(student.hasNext()) {
			Student std = student.next();
			System.out.println(std.name + ", " + std.sno);
		}
		
	}
}

class Student implements Comparable<Student>{
	int sno;
	String name;
	
	public Student(int sno, String name) {
		super();
		this.sno = sno;
		this.name = name;
	}


	// 1. 이름순으로 정렬
//	@Override
//	public int compareTo(Student o) {
//		return this.name.compareTo(o.name);
//	}
	
	// 2. 학번순으로 정렬
//	@Override
//	public int compareTo(Student o) {
//		// return (this.sno < o.sno) ? -1 : ((this.sno == o.sno) ? 0 : 1);
//		return Integer.compare(this.sno, o.sno);
//	}
	
	// 3. 이름과 학번순으로 정렬
	@Override
	public int compareTo(Student o) {
		String x = this.name + this.sno;
		String y = o.name + o.sno;
		return x.compareToIgnoreCase(y);
	}	
}



















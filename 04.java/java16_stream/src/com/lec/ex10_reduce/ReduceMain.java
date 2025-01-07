package com.lec.ex10_reduce;

import java.util.Arrays;
import java.util.List;

public class ReduceMain {

	public static void main(String[] args) {
		// map -> reduce -> MapAndReduce
		List<Student> list = Arrays.asList(
				new Student("홍길동", 90),
				new Student("홍길순", 98),
				new Student("홍길녀", 95)
			);
		
		// 1. 합계구하기(1) - sum()
		int tot = list.stream().mapToInt(s -> s.getScore()).sum();
		System.out.println("합계구하기(1) = " + tot);
		tot = list.stream().mapToInt(Student::getScore).sum();
		System.out.println("합계구하기(1) = " + tot);
		System.out.println();
		
		// reduce의 return타입은 OptionalXXX
		// 2. 합계구하기(2) - reduce(BinaryOperator) : 예외처리를 하지 않음
		//    ... BinaryOperator는 매개변수를 2개 전달 받는 객체
		//    .... 0, 90 -> 90, 98 -> 188, 95 -> 283
		// list = Arrays.asList();
		tot = list.stream().mapToInt(Student::getScore).reduce((x,y) -> x+y).getAsInt();
		System.out.println("합계구하기(2) = " + tot);
		System.out.println();
		
		// 3. 합계구하기(3) - reduce(기본값, BinaryOperator) : 예외처리함
        //	    .... 1000, 90 -> 1090, 98 -> 1188, 95 -> 1283
		// list = Arrays.asList();
		tot = list.stream().mapToInt(Student::getScore).reduce(1000, (x,y) -> x+y);
		System.out.println("합계구하기(3) = " + tot);
	}

}

class Student {
	
	private String name;
	private int score;
	
	public Student(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
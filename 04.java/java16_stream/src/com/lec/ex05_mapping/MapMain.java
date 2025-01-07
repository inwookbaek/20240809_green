package com.lec.ex05_mapping;

import java.util.Arrays;
import java.util.List;

public class MapMain {

	public static void main(String[] args) {
		// 1. map: 특정 요소(값)을 전달 받아서 다른 형태의 stream으로 리턴
		List<Student> list = Arrays.asList(
				new Student("홍길동", 90),
				new Student("홍길성", 80),
				new Student("홍길녀", 85)
			); 
		
		list.stream().mapToInt(Student::getScore).forEach(System.out::println);
		System.out.println("학생들의 총점 = " 
			+ list.stream().mapToInt(Student::getScore).sum());
		System.out.println("총인원수 = " 
				+ list.stream().mapToInt(Student::getScore).count());
		System.out.println("최저점수 = " 
				+ list.stream().mapToInt(Student::getScore).min().getAsInt());
		System.out.println("최고점수 = " 
				+ list.stream().mapToInt(Student::getScore).max());

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
	public int getScore() {
		return score;
	}
}
package com.lec.ex01_intro;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MapAndRecuceMain {

	public static void main(String[] args) {
		List<Student> list = Arrays.asList(
				new Student("홍길동", 95),
				new Student("홍길순", 91),
				new Student("홍길자", 98),
				new Student("홍길상", 92)
			);	
		// 1. 학생들의 평균점수
		//    1step. 중간처리 : mapToInt() 
		//    2step. 최종처리 : average()
		//           결과값리턴 : getAsDouble()
		// step by step으로 처리과정설명(에러가 있어도 예시를 제시)
//		Stream<Student> stream = list.stream();
//		IntStream result1 = stream.mapToInt(std -> std.getScore());
//		Stream result2 =  stream.average();
//		double result3 = result2.getAsDouble();
		
		double result = list.stream()
							.mapToInt(s -> s.getScore())
							.average()
							.getAsDouble();
		System.out.println("학생들의 평균점수 = " + result);
		
		// 2. 학생들의 총점?
		// mapToInt -> sum()
		int sum = list.stream()
					  .mapToInt(s -> s.getScore())
					  .sum();
		System.out.println("학생들의 총점 = " + sum);
		sum = list.stream()
				  .mapToInt(Student :: getScore)
				  .sum();
		System.out.println("학생들의 총점 = " + sum);
		
		// 3. 학생들의 인원수?
		// mapToInt -> count()
		long count = list.stream()
				  		 .mapToInt(s -> s.getScore())
				  		 .count();
		System.out.println("학생 총 인원수 = " + count);
		count = list.stream()
					.mapToInt(Student :: getScore)
					.count();
		System.out.println("학생 총 인원수 = " + count);
	}
}


















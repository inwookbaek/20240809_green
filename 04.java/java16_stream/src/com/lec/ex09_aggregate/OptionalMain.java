package com.lec.ex09_aggregate;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class OptionalMain {

	public static void main(String[] args) {
		
		// List<Integer> list = Arrays.asList();
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		// Integer::valueOf -> int타입을 Integer객체로 즉 i -> Integer.valueOf(1)
		try {
			// 값이 없을 경우에는 NoSuchElementException예외발생
			double result1 = list.stream().mapToInt(Integer::valueOf).average().getAsDouble();			
			System.out.println("[getAsDouble()] 평균 = " + result1);
		} catch (Exception e) {
			System.out.println("list에는 값이 하나도 없습니다!");
		}
		
		// 값이 없을 경우에 try catch처리 이외의 처리방법(1) - isPresent(), 요소존재여부를 리턴
		OptionalDouble result2 = list.stream().mapToInt(Integer::valueOf).average();
		if(result2.isPresent()) {
			System.out.println("[isPresent()] 평균 = " + result2.getAsDouble());
		} else {
			System.out.println("[isPresent()] list에는 값이 하나도 없습니다!");		
		}
		
		// 값이 없을 경우에 try catch처리 이외의 처리방법(2) - orElse(), 요소가 없을 경우 기본값
		double result3 = list.stream().mapToInt(Integer::valueOf).average().orElse(100);
		System.out.println("[orElse(기본값)] 평균 = " + result3);

		// 값이 없을 경우에 try catch처리 이외의 처리방법(3) 
		//  - ifPresent()는 값이 있으면 실행, 없으면 실행되지 않음		
		list.stream()
			.mapToInt(Integer::valueOf)
			.average()
			.ifPresent(avg -> System.out.println("[ifPresent()] 평균 = " + avg));
	}

}




















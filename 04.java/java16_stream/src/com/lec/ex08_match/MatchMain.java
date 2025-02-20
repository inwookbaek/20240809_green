package com.lec.ex08_match;

import java.util.Arrays;

/*
	매칭(allMatch(), anyMatch(), noneMatch())
	
	스트림클래스는 최종처리단계에서 요소들이 특정조건에 맞는지 여부를 조사할 수 있도록
	세가지 매칭메서드를 제공하고 있다.
	
	1. allMatch() : 모든 요소들이 매개값으로 주어진 조건에 만족하는지 여부를 리턴
	2. anyMatch() : 최소한 한 개의 요소가 주어진 조건에 만족여부를 리턴
	3. noneMatch() : 모든 요소들이 만족하지 않는지 여부를 리턴
*/
public class MatchMain {

	public static void main(String[] args) {
		
		int[] int_arr = {2,4,6};
		
		// 1. allMatch
		boolean result = Arrays.stream(int_arr).allMatch(n -> n%2==0);
		System.out.println("[allMatch] 스트림요소 전체가 2의 배수인가? " + result);

		result = Arrays.stream(int_arr).allMatch(n -> n%3==0);
		System.out.println("[allMatch] 스트림요소 전체가 3의 배수인가? " + result);
		
		// 2. anyMatch
		result = Arrays.stream(int_arr).anyMatch(n -> n%3==0);
		System.out.println("[anyMatch] 스트림요소 일부가 3의 배수인가? " + result);
			
		// 3. noneMatch
		result = Arrays.stream(int_arr).noneMatch(n -> n%3==0);
		System.out.println("[noneMatch] 스트림요소중에 3의 배수가 없는가? " + result);
		
		int[] int_arr1 = {2,4,8};
		result = Arrays.stream(int_arr1).noneMatch(n -> n%3==0);
		System.out.println("[noneMatch] 스트림요소중에 3의 배수가 없는가? " + result);

	}

}



















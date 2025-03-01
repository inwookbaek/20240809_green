package com.lec.ex03_compare;

public class CompareOperator {

	public static void main(String[] args) {
		// 비교연산자 : <, > <=, >=, !=, ==
		// 비교연산자의 결과는 boolean타입을 리턴
		int num1 = 10;
		int num2 = 10;
		
		boolean result1 = num1 == num2;
		boolean result2 = num1 != num2;
		boolean result3 = num1 <= num2;
		boolean result4 = num1 >= num2;
		
		System.out.println("num1 == num2 비교결과 = " + (num1 == num2));
		System.out.println("num1 != num2 비교결과 = " + (num1 != num2));
		System.out.println("num1 <= num2 비교결과 = " + (num1 <= num2));
		System.out.println("num1 >= num2 비교결과 = " + (num1 >= num2));
		System.out.println("num1 < num2 비교결과 = " + (num1 > num2));
		System.out.println("num1 > num2 비교결과 = " + (num1 < num2));
		System.out.println();
		
		char char1 = 'A';  // 65
		char char2 = 'B';  // 66
		System.out.println("char1 < char2의 비교결과 = " + (char1 < char2));
		System.out.println("char1 > char2의 비교결과 = " + (char1 > char2));
		System.out.println();
		
		// 비교연산시에도 연산을 수행하기 전에 형변환이 발생해서
		// 피연산자와 타입을 일치 시킨다.
		System.out.println("'A' == 65의 비교결과 = " + ('A' == 65));      // true
		System.out.println("3 == 3.0의 비교결과 = " + (3 == 3.0));        // true
		System.out.println("3.0f == 3.0의 비교결과 = " + (3.0f == 3.0));  // true
		System.out.println();
		
		System.out.println("0.1f == 0.1의 비교결과 = " + (0.1f == 0.1));  // false, 정밀도 문제
		System.out.println(0.1);
		System.out.println(0.1f);
		System.out.println((double)0.1f);
	}

}















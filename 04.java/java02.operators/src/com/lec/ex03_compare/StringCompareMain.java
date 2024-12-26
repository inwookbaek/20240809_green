package com.lec.ex03_compare;

public class StringCompareMain {

	public static void main(String[] args) {
		// String타입의 문자열을 비교할 때는 대소(>,<,<=,>=)연산자는 사용할 수 없고
		// 동등(==,!=)연산자를 사용할 수 있지만 문자열이 같은지 다른지를 비교하는데는
		// 사용하지 않는다.
		// 그 이유는 String은 기본타입자료형이 아니라 참조타입자료형이기 때문이다.
		// 따라서, String변수를 비교할 경우에 동등연산자를 사용하면 원하지 않는 결과가
		// 나올 수 있다.
		
		String str1 = "소향";             // literal
		String str2 = "소향";             // literal
		String str3 = new String("소향"); // 객체 
		System.out.println("str1 = " + str1);
		System.out.println("str2 = " + str2);
		System.out.println("str3 = " + str3);
		System.out.println();
		
		// 비교연산자를 사용할 경우
		// 1. 동등연산자(==)를 사용할 경우
		//    ... 참조타입일 경우 값을 비교하는 것이 아니라
		//        참고하고있는 메모리 주소를 비교한다.
		System.out.println("str1 == str2의 비교결과 = " + (str1 == str2)); // true
		System.out.println("str1 == str3의 비교결과 = " + (str1 == str3)); // false
		System.out.println("str2 == str3의 비교결과 = " + (str2 == str3)); // false
		System.out.println();
		
		// Java는 문자리터럴이 동일하다면 중복된 객체가 아니라 하나의 객체만
		// 참조하도록 설계되어 있다. 
		// Java는 literal이 동일하다면 Constant Pool 메모리영역에 한 개의 값만
		// 생성하도록 설계되어 있다. 따라서, str1과 str2는 동일한 String객체의
		// 값(메모리주소)를 가지고 있다.
		// 그러나, 객체생성연산자인 new로 생성한 str3(참조타입, 객체)는 Heap메
		// 모리 영역에 생성되고 str3는 생성된 객체의 Heap메모리주소를 참조하고
		// 있기 때문에 동등연산자로 비교할 경우에는 str1과 str2는 동일한 주소를
		// 비교하고 str1과 str3는 다른 주소값과 비교하기 때문에 true, false결과
		// 가 나온다.
		// 따라서, 참조타입을 비교할 경우 즉, String객체의 값만 비교할 경우에는
		// 동등연산자를 사용하지 않고 String.equals()메서드를 이용해서 비교해야
		// 한다.
		System.out.println("str1.equals(str2)의 비교결과 = " + str1.equals(str2)); // true
		System.out.println("str1.equals(str3)의 비교결과 = " + str1.equals(str3)); // true
		System.out.println("str2.equals(str3)의 비교결과 = " + str2.equals(str3)); // true
	}

}



































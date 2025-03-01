package com.lec.ex02_arithmetic;

public class StringConcatMain {

	public static void main(String[] args) {
		// +연산자는 더하기연산자, 부호연산자이면서 문자열연결연산자
		// + : 문자열 연결연산자
		// 문자열의 연산의 결과는 String(문자열)타입이다.
		
		String str1 = "JDK" + 17.0;
		System.out.println("JDK + 17.0 = " + ("JDK" + 17.0));
		
		String str2 = str1 + "의 특징";
		System.out.println("str1 + 의 특징 = " + (str1 + "의 특징"));
		
		String str3 = "JDK" + 17.0 + 0.3;
		System.out.println("JDK + 17.0 + 0.3 = " + ("JDK" + 17.0 + 0.3));
		
		String str4 = 17.0 + 0.3 + "JDK";
		System.out.println("17.0 + 0.3 + JDK = " + (17.0 + 0.3 + "JDK"));
		
	}

}

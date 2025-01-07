package com.lec.ex04_typechange;

public class PromotionMain {

	public static void main(String[] args) {
		// 2. 자동형변환
		byte b1 = 10;  // int(10) -> byte로 형변환한 -> b1에 저장
		int i1 = b1;   // byte(b1) -> int로 자동형변환
		System.out.println("i1 의 값 = " + i1);
		
		char c1 = '가';
		i1 = c1;
		System.out.println("i1 의 값 = " + i1);
		
		long l1 = i1; // int -> long
		System.out.println("l1 의 값 = " + l1);
		
		float f1 = l1;  // long -> floart
		System.out.println("f1 의 값 = " + f1);
		
		double d1 = f1; // float -> double
		System.out.println("d1 의 값 = " + d1);		

	}

}

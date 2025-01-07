package com.lec.ex05_accuracy;

public class FloatDoubleMain {

	public static void main(String[] args) {
		// 연산시 정밀도 문제
		int i1 = 123456780;
		int i2 = 0;
		
		// 형변환될 때 정밀도
		// int(123456780)가 float로 형변환의 결과 = 1.23456784E8(123456784) 
		float f1 = i1;
		System.out.println("f1의 값 = " + f1);
		System.out.println("f1 - i1의 값 = " + (f1-i1));
		
		i2 = (int) f1;
		System.out.println("i2의 값 = " + i2); 
		
		System.out.println("i1 - i2의 결과 = " + (i1-i2));

		// 실수연산시 정밀도
		System.out.println("double의 연산 결과 = " + (1.0 + 0.1));
		
		// double(0.1) -> float(0.1f)로 강제형변환 -> double(0.1f)변환 -> double + double
		System.out.println("0.1f = " + 0.1f);
		System.out.println("double과 float의 연산 결과 = " + (1.0 + 0.1f));
				
		System.out.println("1.0f = " + 1.0f);
		System.out.println("float과 float의 연산 결과 = " + (1.0f + 0.1f));
	}

}

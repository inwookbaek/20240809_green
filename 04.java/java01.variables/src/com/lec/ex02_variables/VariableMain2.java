package com.lec.ex02_variables;

public class VariableMain2 {

	public static void main(String[] args) {
		// 1. 변수 동시선언
		int a, b, c;
		// System.out.println("a = " + a); 에러
		
		int x=10, y=20, z=30;
		System.out.println("x = " + x);
		
		int l, m, n = 10; // n만 선언과 초기화, l, m은 선언만
		// int l = m = n = 40; // 에러
		
		// 2. 변수선언과 변수타입
		// a. 기본타입(premitive)
		// 1) 정수 : byte(1) < char(2) < short(2) < int(4) < long(8) < float(4) < double(8)
		// 자바에서 정수의 기본타입은 int, 실수의 기본타입은 double
		
		// 가. byte : -128~127까지의 숫자만 저장이 가능
		byte b1;   
		byte b2;   
		b1 = -128;
		b2 = 127;
		System.out.println("b1 = " + b1 + ", b2 = " + b2);
		
		// b1 = -129;  에러 : 저장범위(최소)를 벗어남
		// b2 = 128;   에러 : 저장범위(최대)를 벗어남
		b1 = (byte) -129;  // 강제형변환 기본타입인 int(4)에 byte(1)로 강제형변환
		b2 = (byte) 128;   // 강제형변환 기본타입인 int(4)에 byte(1)로 강제형변환
		System.out.println("b1 = " + b1 + ", b2 = " + b2);
		
		b1 = (byte) -7986987; 
		b2 = (byte) 7986987;   
		System.out.println("b1 = " + b1 + ", b2 = " + b2);
		System.out.println();
		
		// 나. int : -21억 ~ 21억, long
		System.out.println("int의 최소값 = " + Integer.MIN_VALUE + 
				           ", int의 최대값 = " + Integer.MAX_VALUE);
		int i1 = -2147483648; // -21^31 
		int i2 = 2147483647; // 21^31 - 1
		System.out.println("i1 = " + i1 + ", i2 = " + i2);
		
		// i1 = -2147483649; 에러 : 저장범위(최소)를 벗어남
		// i2 = 2147483648;  에러 : 저장범위(최대)를 벗어남
		System.out.println();
		
		// 다. long
		System.out.println("long의 최소값 = " + Long.MIN_VALUE + 
		           ", long의 최대값 = " + Long.MAX_VALUE);		
		long l1; // -2^61
		long l2; // 2^61 - 1

		l1 = -2147483649L; // 정수의 기본타입인 int를 long타입으로 선언은 대문자 L or 소문자 l로 선언
		l2 = 2147483648l;
		System.out.println("l1 = " + l1 + ", l2 = " + l2);
		
//		l1 = (long) -2147483649; 에러
//		l2 = (long) 2147483648;  에러
		System.out.println();
		
		// 2) 실수 : float, double
		// 실수의 기본타입은 double
		// 따라서 float데이터 타입으로 선언하려면 실수 뒤에 F or f로 선언
		// 실수를 double로 선언하려면 생략(기본값)하거나 실수 뒤에 D or d로 선언
		// 하거나 형변환(강제, 자동)을 해야 한다.
		System.out.println("float의 최소값 = " + Float.MIN_VALUE + 
		           ", float의 최대값 = " + Float.MAX_VALUE);			
		System.out.println("double의 최소값 = " + Double.MIN_VALUE + 
		           ", double의 최대값 = " + Double.MAX_VALUE);
		
		// 가. float
		// float f1 = 1.0;  // 실수의 기본타입은 double이기 때문에 float로 변환(선언) 해야 한다.
		float f1 = 1.0F;  // 실수의 기본타입은 double이기 때문에 float로 변환(선언) 해야 한다.
		float f2 = 1.0f;
		System.out.println("f1 = " + f1 + ", f2 = " + f2);
		
		// 나. double
		double d1 = 1.0;
		double d2 = 1.0D;
		double d3 = 1.0d;
		double d4 = 3.141592;  
		d4 = 3.141592D;  
		d4 = 3.141592d;
		System.out.println("d1 = " + d1 + ", d2 = " + d2 + ", d3 = " + d3 + ", d4 = " + d4);
		System.out.println();
		
		// 3) 논리형(boolean(1byte))
		// 값은 true, false, 내부적으로는 true = 1, false = 0의 정수값을 가진다.
		boolean bool1 = true;
		boolean bool2 = false;
		System.out.println("bool1 = " + bool1 + ", bool2 = " + bool2);
		
		// b. 참조타입(reference)
		// 별도로 강의예정

	}
}

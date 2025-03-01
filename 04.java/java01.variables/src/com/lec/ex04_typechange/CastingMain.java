package com.lec.ex04_typechange;
/*
	형변환(Casting(강제형변환), Promotion(자동형변환))이란?
	
	모든 리터럴과 변수는 데이터타입이 있다. 프로그램을 작성하다 보면 서로 다른 데이터타입의
	값을 연산하는 경우가 자주 발생하는데 모든 연산은 기본적으로 동일 타입의 데이터만 연산할
	수 있기 때문에 서로 다른 타입의 연산을 수행하는 경우에는 연산을 수행하기 전에 형변환을 
	통해 동일 타입의 데이터타입으로 변경해야 한다.
	
	형변환이란? 변수 또는 리터럴의 데이터 타입을 다른 데이터 타입으로 변환하는 것을 말한다.
	큰 크기의 데이터타입에서 작은 크기의 데이터타입으로 변환하는 것을 '강제형변환(Casting,
	명시적)'이라 하고 작은 크기에서 큰 크기의 데이터타입으로 변환하는 것을 '자동형변환(
	Promotion, 묵시적)'이라고 한다.
	
	byte(1)< char(2) < short(2) < int(4) < long(8) < float(4) < double(8)
	
	자동형변환은 자동으로 변환되기 때문에 개발자가 별도로 정의할 것이 없지만 수동형변환은
	개발자가 명시적으로 선언해야 한다. Casting방법은 아래와 같다.
	
	데이터타입 변수명 = (테이터타입) 값 [or 표현식(변수명 or 연산수행)]
	
	a. double d1 = int_value + double_value;  // int_value가 double로 자동형변환후 연산결과가 double로 저장
	b. double d1 = (double) int_value + double_value;
	c. double d1 = int_value + (int) double_vlaue;
	d. double d1 = (double) (int_value + (int) double_value);

*/
public class CastingMain {

	public static void main(String[] args) {
		
		// 1. 강제형변환(casting)
		float f1 = (float) 3.141592; // double타입인 3.141592를 float로 강제형변환
		int i1 = 44032;  // '가' = 44032
		char c1 = '가';
		c1 = (char) i1;
		System.out.println(c1 + " 의 내부값 = " + i1);
		System.out.println(i1 + " 의 내부값 = " + i1);
		System.out.println((char) i1 + " 의 내부값 = " + (char) i1);
		System.out.println();
		
		long l1 = 500;  // int타입인 500이 long타입으로 자동형변환 즉, int -> long
		i1 = (int) l1;  // long(l1) -> int로 강제형변환후 i1에 대입
		System.out.println("i1 의 값 = " + i1);
		System.out.println("l1 의 값 = " + l1);
		System.out.println();
		
		double d1 = 3.141592; // 형변환이 발생되지 않고 값만 대입
		System.out.println("d1의 값 = " + d1);
		System.out.println();
		
		// 2. 강제형변환시 값이 짤리는 경우
		i1 = (int) d1;
		System.out.println("i1 의 값 = " + i1);
		
		byte b1 = (byte) 128; // int -> byte
		System.out.println("b1 의 값 = " + b1);
		b1 = (byte) 1000; // int -> byte
		System.out.println("b1 의 값 = " + b1);
		System.out.println();
		
		// 3. 다른 타입의 연산
		double d2 = 10 + 3.141592; // int(10) -> double로 자동변환 + double(3.141592) 연산
		System.out.println("d2 의 값 = " + d2);
		
		d2 = 10 + (int) 3.141592; 
		System.out.println("d2 의 값 = " + d2);
		
		d2 = (int) (10 + 3.141592); // 10 -> 10.0 + 3.141592 -> int
		System.out.println("d2 의 값 = " + d2);
		
		i1 = (int) (10 + 3.141592);
		System.out.println("i1 의 값 = " + i1);
		
	}

}


















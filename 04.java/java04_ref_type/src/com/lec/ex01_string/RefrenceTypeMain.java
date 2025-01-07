package com.lec.ex01_string;
/*
	1. 데이타타입분류
	   
	   1) 기본타입(premitive type)
	   
	       정수(byte, char... in, long), 실수(float, double), 논리(boolean)로 실제 값을 가지고 있는 데이터 타입
	       
	   2) 참조타입
	   
	      객체가 저장되어 있는 주소를 참조하고 있는 데이터타입으로 Array(배열), Enum(열거), Class(클래스, 대표적
	      String), Interface(인터페이스)가 있다.
	      
	   3) 기본 vs 참조
	   
	      기본타입은 실제값을 변수에 저장하는 반면에 참조타입은 객체가 저장되어 있는 메모리(힙영역)의 주소를
	      저장하고 있는 데이터 타입이다. 주소를 통해 객체를 참조한다는 의미에서 참조타입이라고 한다.
	      
	      자바는 문자열을 String이라는 참조타입(class)변수에 저장하기 때문에 String변수를 우선 선언해야 한다.
	      실제로는 문자열을 String변수에 저장한다는 말은 틀린 말이다. 문자열이 직접 변수에 저장되는 것이 아니라
	      문자열이 String객체로 생성되어 있는 Heap메모리 영역에 생성되고 String변수는 String객체가 저장되어 있는
	      힙영역의 메모리주소를 참조한다.
	      
	      자바는 문자열리터럴이 동일하다면 Stirng리터럴을 공유하도록 설계되어 있다. 일반적으로 변수에 문자를
	      저장할 경우에는 문자열 리터럴을 사용하지만 객체 생성연산자인 new를 사용해서 직접 String객체를 생성할
	      수 있다.
	      
	      new 객체생성연산자는 힙영역에 새로운 객체를 생성할 때 생성하는 연산자로 "객체생성연산자"라고 한다.
	
	2. 메모리사용영역
	   
	   1) stack영역
	   
	      선언된 변수가 저장되는 메모리영역을 말한다.	   
	   
	   2) Heap영역
	   
	      힙영역은 객체와 배열이 생성(저장)되는 메모리 영역이다. 힙영역에 생성된 객체와 배열은
	      스택영역의 변수나 다른 객체의 필드에서 참조된다. 참조하는 변수나 필드가 없다면 의미가 
	      없는 객체가 되기 때문에 이런 객체를 쓰레기(garbage)로 취급되고 JVM의 쓰레기수집기(garbage
	      collector)를 실행 시켜서 객체를 힙영역에서 자동으로 제거한다. JVM이 자동으로 삭제해 주기
	      때문에 개발자는 별도의 코드를 작성할 필요가 없다.
	      
	   3) Method영역
	   
	      코드에서 사용되는 클래스파일(~.class)들은 클래스로더(class loader)가 읽어서 상수, 필드,
	      메서드, 생성자등을 구분해서 메서드영역에 저장해 둔다. 이 영역은 모든 쓰레드(프로그램)가
	      공유하는 영역이다.
	
	   4) Constant Pool
	   
	      리터럴이 저장되는 메모리 영역이다.
*/
public class RefrenceTypeMain {

	public static void main(String[] args) {
		
		double d1 = Math.random();
		double d2 = java.lang.Math.random();
		
		String str1 = new String("소향");
		java.lang.String str2 = new java.lang.String("소향");
		
		RefrenceTypeMain myclass1 = new RefrenceTypeMain();
		com.lec.ex01_string.RefrenceTypeMain myclass2 = new com.lec.ex01_string.RefrenceTypeMain();
		
		// 객체.hashCode()메서드는 해당 객체가 Heap영역에 저장되어 있는 메모리 주소를 10진수로 리턴
		System.out.println("str1.hashCode() = " + str1.hashCode());
		System.out.println("str2.hashCode() = " + str2.hashCode());
		System.out.println("myclass1.hashCode() = " + myclass1.hashCode());
		System.out.println("myclass2.hashCode() = " + myclass2.hashCode());
		
		String s1 = new String("홍길동"); // 객체 - Heap영역
		String s2 = "홍길동";             // 리터럴 - CP영역 
		String s3 = "홍길동";			  // 리터럴 - CP영역(s2와 동일한 값(리터럴)			
		String s4 = new String("홍길동"); // 객체 - Heap영역
		s4 = null;  // s4에 할당된 객체는 쓰레기객체(JVM gc가 임의의시간에 메모리에서 삭제)		
		System.out.println();
		
		int i1 = 10;
		String name1 = "소향";
		String name2 = "소향";
		String name3 = new String("소향");
		String name4 = new String("소향");
		System.out.println("name1.hashCode() = " + name1.hashCode());
		System.out.println("name2.hashCode() = " + name2.hashCode());
		System.out.println("name3.hashCode() = " + name3.hashCode());
		System.out.println("name4.hashCode() = " + name4.hashCode());
		System.out.println();
		
		// 동등연산자
		System.out.println("name1 == name2의 비교결과 = " + (name1==name2));
		System.out.println("name1 == name3의 비교결과 = " + (name1==name3));
		System.out.println();
		
		// 값만비교할 경우 equals()
		System.out.println("name1.equals(name2)의 비교결과 = " + (name1.equals(name2)));
		System.out.println("name1.equals(name3)의 비교결과 = " + (name1.equals(name3)));
		System.out.println();
		
		// Object : Java에서 최상위 객체 
		Object obj1 = new Object();
		Object obj2 = new Object();
		System.out.println("obj1.hashCode() = " + obj1.hashCode());
		System.out.println("obj2.hashCode() = " + obj2.hashCode());
		System.out.println();
		
		RefrenceTypeMain rt1 = new RefrenceTypeMain();
		RefrenceTypeMain rt2 = new RefrenceTypeMain();
		System.out.println("rt1.hashCode() = " + rt1.hashCode());
		System.out.println("rt2.hashCode() = " + rt2.hashCode());
		System.out.println();
	}
	
	public void method1() {}
	public static String method2() {
		return "소향";
	}

}

















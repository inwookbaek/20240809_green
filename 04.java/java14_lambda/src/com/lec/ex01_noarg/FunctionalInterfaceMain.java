package com.lec.ex01_noarg;

/*
	람다식(Lambda Expression)
	
	자바는 함수형프로그래밍을 위해 Java8버전부터 람다식을 지원하면서 기존의 코딩 패턴이 많이 달라 졌다.
	람다식은 수학자 알론조 처리(Alonzo Church)가 발표한 계산법에서 사용된 수학식의 표현으로 이를 제자인
	존 메카시(Jjon McCarthy)가 프로그래밍언어에 도입했다.
	
	람다식은 익명함수를 생성하기 위한 식으로 객체지형언어보다 함수지향언어에 가깝다. 자바에 람다식을
	수용한 이유가 자바코드가 매우 간결해 지고 컬렉션의 요소를 필요하거나 매핑에서 원하는 결과를 쉽게 
	집계할 수가 있기 때문이다. 람다식의 형태는 매개변수를 가진 코드블럭이지만 런타임시에는 익명구현
	객체를 생성한다.
	
	1. 기본문법
	
	   (arg,...) -> { 실행문...}
	   
	   람다식에서는 런타임시에 대입되는 값에 따라 자동으로 인식될 수 있기 때문에 람다식에서는 매개변수의
	   타입을 일반적으로 정의하기 않는다. 예를 들어 매개변수가 하나가 있을 경우에는 아래와 같이 정의할 수
	   있다.
	   
	      (int a) -> { System.out.println(a); }
	   or a -> { System.out.println(a); }  : 소괄호가 없는 경우는 매개변수가 하나만 있을 경우에만 생략가능
	   or a -> System.out.println(a);      : 중괄호가 없는 경우는 실행문장이 하나만 있을 경우에만 생략가능
	   
	2. 타겟함수와 함수적인터페이스
	
	   람다식형태는 매개변수를 가진 코드블럭이기 때문에 자바의 메서드를 선언하는 것 처럼 보여지지만 자바는
	   메서드를 단독으로 사용할 수 없고 항상 클래스이 멤버로 선언하기 때문에 람다식은 단순히 메서드를 선언
	   하는 것이 아니라 객체(익명)를 생성한다.
	   
	   람다식은 인터페이스 변수에 대입된다. 이 의미는 "람다식은 익명구현객체를 생성한다"라는 의미이다. 인터
	   페이스는 직접 객체생성할 할 수 없기 때문에 구현클래스가 필요한데 람다식은 구현하는 클래스 선언없이 
	   직접 익명구현객체를 생성해서 객체화(인스턴스화)할 수 있다.
	   
	   람다식은 대입될 인터페이스의 종류에 따라 달라지기 때문에 람다식디 대입될 인터페이스를 람다식의 타겟
	   타입(Target Type)이라고 한다.	
	
	3. 함수적인터페이스(Functional Interface)
	
	   모든 인터페이스를 람다식의 타겟타입으로 사용할 수 없다. 람다식이 하나의 메서드만 정의하기 때문에 두
	   개 이상의 추상메서드가 선언된 인터페이스는 람다식으로 사용할 수 없다.
	   
	   하나의 추상메서드가 선언된 인터페이스만이 람다식의 타겟타입이 될 수 있는데 이러한 인터페이스를 
	   "함수적 인터페이스 Functional Interface"라고 한다. 함수적인터페이스를 작성할 때 2개 이상의 추상메서드
	   가 선언되지 않도록 컴파일러가 확인할 수 있도록 하기 위해서는 명시적으로 @FunctionalInterface 애너
	   테이션을 선언해야 한다.
*/
public class FunctionalInterfaceMain {

	public static void main(String[] args) {
		
		// 인터페이스는 객체를 생성할 수 없다.
		// 객체생성할 경우 구현객체를 생성하거나 익명구현객체를 생성해야 한다.
		
		// 1. 타겟타입(참조타입 MyFuncInterface)과 구현객체(mfi 즉, MyImplClass)
		MyFuncInterface mfi1 = new MyImplClass();
		mfi1.method1();
		
		// 2. 타겟타입과 익명(구현)객체
		Runnable run = new Runnable() {
			public void run() {}
		};
		
		MyFuncInterface mfi2 = new MyFuncInterface() {
			@Override public void method1() {}
			@Override public void method2() {}
		};
		
		
		// 3. 람다식
		MyFuncInterface2 fi1 = new MyFuncInterface2() {
			@Override public void method1() { System.out.println("fi1는 FuntionalInterface.metho1()을 구현!!! (익명구현객체)");}
		};
		fi1.method1();
		
		MyFuncInterface2 fi2 = () -> { System.out.println("fi2는 FuntionalInterface.metho1()을 구현!!! (람다식)"); };
		fi2.method1();
		
		MyFuncInterface2 fi3 = () -> System.out.println("fi3는 FuntionalInterface.metho1()을 구현!!! (람다식-중괄호생략)");
		fi3.method1();
	}

}

interface MyFuncInterface {
	void method1();
	void method2();
}

class MyImplClass implements MyFuncInterface {

	@Override
	public void method1() {}
	public void method2() {}
}

@FunctionalInterface
interface MyFuncInterface2 {
	void method1();
}

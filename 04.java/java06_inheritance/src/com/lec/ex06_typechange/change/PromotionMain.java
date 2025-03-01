package com.lec.ex06_typechange.change;

/*
	참조타입의 형변환(자동형변환, 강제형변환)과 다형성
	
	자동형변환 자식->부모, 강제형변환 부모->자식으로 형변환하는 것을 말한다.
	
	다형성이란? 동일 타입이지만 실행결과가 다양한 형태의 객체를 사용할 수 있는 성질을 말한다.
	프로그램측면에서 보면 다형성은 하나의 타입에 여러형태의 객체를 대입함으로써 다양한 기능을
	수행할 수 있게 한다.
	
	다형성을 위해서 Java는 자식클래스가 부모클래스로 타입변환하는 것을 허용한다. 즉, 부모
	타입에 모든 자식객체를 대입할 수 있다는 것이다. 이 기능을 이용한다면 모든 객체를 부품화
	할 수 있다.
	
	타입변환이란? 데이터타입을 다른 데이터타입으로 변환하는 행위를 말한다. 기본 데이터
	타입의 변환처럼 참조타입(interface, class, array, enum)도 타입변환이 가능하다. 
	클래스타입변환은 직접적인 상속관계가 있는 클래스 사이에서만 타입변환이 가능하다.
	
	자동타입변환(promotion)은 프로그램 실행도중에 자동으로 타입변환이 일어나는 것을 말한다.
	자동타입변환의 개념은 자식은 부모의 속성과 기능을 상속받기 때문에 부도와 동일하게 처리
	될 수 있다는 것이다.
	
	주의할 점은 자동형변환이 된 후에는 부모클래스에 선언된 필드와 메서드만 접근이 가능하다.
	비록 변수는 자식객체를 참조하지만 변수로 접근 가능한 멤버는 부모클래스의 멤버로만 제한
	이 된다. 그러나 예외가 있는데 메서드가 자식클래스에서 재정의되었다면 부모클래스의 메서드
	가 호출되는 것이 아니라 자식객체의 메서드가 호출된다.
	

*/
public class PromotionMain {

	public static void main(String[] args) {
	
		method(1);
		method(1L);
		method(1f);
		method((char) 65);
		
		method1(new Vechile());
		method1(new Car());
		method1(new Bus());
		method1(new 오토바이());
		System.out.println();
		
		A a = new A();
		B b = new B();
		C c = new C();
		D d = new D();
		E e = new E();
		
		// A. 자동형변환
		// 1. 직접 상속관계가 있을 경우는 가능
		a = b;
		a = c;
		a = d;
		a = e;
		
		b = d;
		c = e;
		
		// 2. 직접상속관계가 없을 경우는 불가능
		// b = e;
		// c = d;
		
		// B. 메서드의 매개변수로 전달
		method_a(1);
		method_a("소향");
		
		a = new A();
		b = new B();
		c = new C();
		d = new D();
		e = new E();
		
		method_a(a);
		method_a(b);
		method_a(c);
		method_a(d);
		method_a(e);
		
		// method_b(a);
		method_b(b);
		// method_b(c);
		method_b(d);
		// method_b(e);

	}
	
	static void method_a(double a) { System.out.println("숫자 = " + a); }
	static void method_a(String a) { System.out.println("문자 = " + a.getClass()); }
	static void method_a(A a) { System.out.println("A = " + a.getClass()); }
	static void method_b(B b) { System.out.println("B = " + b.getClass()); }
	

	static void method(double d) {}
	static void method1(Vechile car) {
		car.run();
	}
}
class Vechile {
	void run() { System.out.println("Vechile.run!!!");}
}
class Car extends Vechile { 
	void run() { System.out.println("Car.run!!!");} 
}
class Bus extends Car {
	void run() { System.out.println("Bus.run!!!");} 
}
class 오토바이 extends Bus {
	void run() { System.out.println("오토바이.run!!!");} 
}


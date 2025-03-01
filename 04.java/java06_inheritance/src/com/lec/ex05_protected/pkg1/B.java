package com.lec.ex05_protected.pkg1;

public class B {

	public B() {}
	B(int xxx) {}     // default
	
	void method_b() { // default
		
		// 동일패키지에 있는 다른 클래스에 protected로
		// 선언된 멤버(필드,생성자,메서드)는 접근이 가능
		A a = new A();          // protected 생성자 접근가능
		a.field1 = "홍길동";    // protected 필드 접근가능
		// a.field2 = "홍길순"; // private 필드 접근불가
		
		a.method_a(); // protected 메서드 접근가능      
	}
}

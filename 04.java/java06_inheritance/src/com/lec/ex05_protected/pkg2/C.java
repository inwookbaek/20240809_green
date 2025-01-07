package com.lec.ex05_protected.pkg2;

import com.lec.ex05_protected.pkg1.*;

public class C {

	void method_c() {
		// 다른 패키지에 있는 상속관계가 없는 클래스에 protected or
		// default로 선언된 멤버(필드,생성자,메서드) 접근불가
		
		// A a1 = new A();    // protected 생성자 접근 불가
		// A a2 = new A(10);  // default 생성자 접근 불가
		A a = new A("소향");  // public 생성자 접근 가능
		
		// a.method_a(); // protected 메서드 접근 불가
		// a.method_x(); // default 메서드 접근 불가
		a.method_y();    // public 메서드 접근 가능
		
		B b1 = new B();      // public 생성자 접근 가능
		// B b2 = new B(10); // default 생성자 접근 불가
		
		// b1.method_b();    // default 메서드 접근 불가
	}
}

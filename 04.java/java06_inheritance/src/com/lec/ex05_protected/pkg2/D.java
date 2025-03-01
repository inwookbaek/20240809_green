package com.lec.ex05_protected.pkg2;

import com.lec.ex05_protected.pkg1.*;

public class D extends A {

	public D() {
		// super();
		// 다른 패키지에 있는 부모클래스에 protected로 선언된
		// 멤버(필드,생성자,메서드)는 직접(super)접근가능
		// A a1 = new A();    // protected 생성자 접근 불가
		// A a2 = new A(10);  // default 생성자 접근 불가
		A a3 = new A("소향");  // public 생성자 접근 가능
		
		// 생성된 객체에 protected or default로 선언된 필드, 
		// 메서드 접근불가
		// a3.field1 = "손흥민";  // protected 필드 접근 불가
		// a3.method_a();         // protected 메서드 접근 불가
		// a3.method_x();         // default 메서드 접근 불가
		a3.method_y();
		
		// protected로 선언된 필드, 메서드는 직접(super)접근 가능
		super.field1 = "손흥민";
		super.method_a();
		// super.method_x();  // default
		super.method_y();
		
		this.field1 = "부모객체가 생성된 후에 접근이 가능";
		this.method_a(); // 부모객체가 생성된 후에 접근가능
	}
}

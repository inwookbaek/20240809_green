package com.lec.ex17_access.field.pkg1;

// 필드의 접근제한자는 public, default, private만 선언가능
// 동일패키지에서 다른 클래스의 필드와 메서드에 접근하려면
// public, default만 접근가능하고 다른 패키지에서 접근할 경우는
// public만 가능
public class A {
	
	public  int field1;  
	        int field2;   // default
	private int field3;
	
	public  void method1() {}
	        void method2() {}
	private void method3() {}
	
	public A() {
		// 1. 필드
		
		field1 = 10;
		field2 = 20;
		field3 = 30;
		
		// 2. 메서드
		method1();
		method2();
		method3();
	}
}

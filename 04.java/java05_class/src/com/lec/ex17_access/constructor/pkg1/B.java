package com.lec.ex17_access.constructor.pkg1;

public class B {

	// 동일패키지에서 다른 클래스의 생성자가 public, protected, 
	// default로 선언된 생성자는 접근이 가능
	// 접근가능
	A a1 = new A(false);     // public
	A a2 = new A(3.141592);  // protected
	A a3 = new A(3);  		 // default  
	// A a4 = new A("");     // private
}

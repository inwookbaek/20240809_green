package com.lec.ex17_access.constructor.pkg2;

import com.lec.ex17_access.constructor.pkg1.A;

public class C {
	
	// 다른 패키지에 있는 클래스의 생성자는 public으로 선언된
	// 경우에만 접근가능, public 이외의 접근제한자로 선언된
	// 생성자는 접근불가(단, 다른 패키지의 클래스는 import되어야 한다)
	A a1 = new A(false);        // public
	// A a2 = new A(3.141592);  // protected
	// A a3 = new A(3);  		// default  
	// A a4 = new A("");        // private
}

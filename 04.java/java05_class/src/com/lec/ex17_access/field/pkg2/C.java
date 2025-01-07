package com.lec.ex17_access.field.pkg2;

import com.lec.ex17_access.field.pkg1.A;

public class C {

	public C() {
		
		A a = new A();
		
		a.field1 = 100;     // public
		// a.field2 = 200;  // default
		// a.field3 = 300;  // private
		
		a.method1();        // public
		// a.method2();     // default
		// a.method3();     // private
	}
}

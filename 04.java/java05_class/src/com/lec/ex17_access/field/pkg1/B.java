package com.lec.ex17_access.field.pkg1;

public class B {

	public B() {

		A a = new A();
		
		a.field1 = 100;     // public
		a.field2 = 200;     // default
		// a.field3 = 300;  // private
		
		a.method1();        // public
		a.method2();        // default
		// a.method3();     // private

	}
}

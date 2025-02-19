package com.lec.ex04_method;

public class UsingThis {
	int outterfield = 10; // 클래스멤버
	
	public class Inner {
		int innerfield = 20;  // 인스턴스(객체)멤버
		void method() {       // 인스턴스(객체)멤버
			MyInterface fi = null;
			fi = () -> {
				System.out.println("outter필드 " + outterfield);
				System.out.println("outter필드 " + UsingThis.this.outterfield);
				
				System.out.println("inner필드 " + innerfield);
				System.out.println("inner필드 " + this.innerfield);
			};
			fi.method();
		}
	}
}

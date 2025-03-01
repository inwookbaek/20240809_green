package com.lec.ex17_access.constructor.pkg1;

/* 
	생성자의 접근제한자는 public, private, protectd, default
	아무것도 선언되어 있지 않은 경우 default접근제한자로 선언
*/
public class A {
	
	public    A(boolean x) {}
	protected A(double x) {}
	          A(int x) {}         // default
	private   A(String x) {}
	
	A a1 = new A(false);     // public
	A a2 = new A(3.141592);  // protected
	A a3 = new A(3);  		 // default
	A a4 = new A("");        // private
}

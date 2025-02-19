package com.lec.ex01_class;

public class A {

	// 생성자
	public A() { System.out.println("A객체 생성!!"); }
	
	// 1. 중첩클래스(인스턴스(객체)멤버)
	//    즉, 객체가 생성되어야만 접근할 수 있는 속성
	public class B {
		String b_field1;
		static String b_field2;
		public B() { System.out.println("B객체 생성!!");}
		void b_method1() { System.out.println("B.b_method1() 호출!!!");}
		static void b_method2() { System.out.println("B.b_method2() 호출!!!");}
	}
	
	// 2. 중첩클래스(클래스멤버 즉, static class)
	//    즉, 클래스명으로 직접 접근가능(객체를 생성하지 않아도 접근가능)
	public static class C {
		String c_filed1;
		static String c_filed2;
		public C() { System.out.println("C객체 생성!!");}
		void c_method1() { System.out.println("[일반] C.c_method1() 호출");}
		static void c_method2() { System.out.println("[정적] C.c_method2() 호출");}
	}
	
	
	void a_method1()  {
		B.b_field2 = "접근가능";
		B.b_method2();
	}
	
	// 3. 중첩클래스(로컬(지역))
	void a_method2() {
		// 로컬중첩클새스는 public으로 선언 불가, 왜냐하면 메서드가 호출후
		// 실행이 종료가 되면 메모리에 삭제가 되기 때문
		class D {
			String d_field1;
			static String d_field2;
			public D() { System.out.println("D객체 생성!!");}
			void d_method1() {System.out.println("[일반] D.method1() 호출!!");}
			static void d_method2() { System.out.println("[정적] D.method2() 호출!!");}	
		}  
		
		D d = new D();
		d.d_field1 = "접근가능";
		d.d_field2 = "접근가능";
		d.d_method1();
		d.d_method2();
		
		class E {}
		class F {
			class G {}
		}
		class G {}
	}
}















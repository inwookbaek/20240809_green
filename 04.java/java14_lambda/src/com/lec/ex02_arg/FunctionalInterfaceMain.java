package com.lec.ex02_arg;

public class FunctionalInterfaceMain {

	public static void main(String[] args) {

		MyInterface fi = null;
		
		fi = new MyInterface() {		
			@Override public void method1(int a) { System.out.println("a = " + a); }
		};
		fi.method1(10);
		
		fi = (int a) -> { System.out.println("a = " + a); };
		fi.method1(10);
		// fi = (String a) -> System.out.println("a = " + a); (x)
		
		// 실행문이 하나만 있을 경우 중괄호 생략가능
		fi = (a) -> System.out.println("a = " + a);
		fi.method1(10);

		// 매개변수가 하나만 있을 경우는 소괄호 생략가능
		fi = a -> System.out.println("a = " + a);
		fi.method1(10);
		
		// 실행문이 여러개 있을 경우
		fi = a -> {
			int result = a * 10;
			System.out.println("fi매개변수 한개, 실행문여러개의 결과 = " + result);
		};
		fi.method1(10);

	}

}


@FunctionalInterface
interface MyInterface {
	void method1(int a);
}
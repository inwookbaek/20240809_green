package com.lec.ex03_return;

public class FunctionalInterfaceMain {

	public static void main(String[] args) {
		// 한 개이상의 매개변수와 return이 있을 경우
		MyInterface fi = null;
		int result = 0;
		
		// 1. 익명구현객체
		fi = new MyInterface() {
			@Override public int method1(int x, int y) { return x + y; }
		};
		result = fi.method1(10, 10);
		System.out.println("int method1(int x, int y) { return x + y; } = " + result);

		// 2. 람다식
		fi = (int a, int b) -> {  return a + b; };
		System.out.println("fi = (int a, int b) -> {  return a + b; } = " + fi.method1(10, 10));

		// 실행문 1개 중괄호생략
		// return있을 경우 중괄호 생략 불가, 대신 return생략시에는 중괄호 생략가능
		// 매개변수 1개이상일 경우 소괄호 생략불가
		fi = (a, b) -> a + b;
		System.out.println("fi = (a, b) -> a + b = " + fi.method1(10, 10));
		
		fi = (x, y) -> x - y;
		System.out.println("(x, y) -> x - y = " + fi.method1(10, 10));
		
		fi = (x, y) -> x * y;
		System.out.println("(x, y) -> x * y = " + fi.method1(10, 10));
		
		fi = (x, y) -> x / y;
		System.out.println("(x, y) -> x / y = " + fi.method1(10, 10));
		System.out.println();
		
		fi = (x, y) -> {
			if(y == 0) {
				System.out.println("나누기연산은 0으로 나눌 수가 없습니다!");
				return 0;
			}
			return x / y;
		};
		System.out.println("(x, y) -> x / y = " + fi.method1(10, 0));
		System.out.println();
		
		// 람다식에 함수를 대입할 수 있다.
		fi = (x, y) -> (int) div(10, 0);
		System.out.println("div(10, 0) = " + fi.method1(10, 0));
		System.out.println();
		
		fi = (x, y) -> (int) div(10, 5);
		System.out.println("div(10, 0) = " + fi.method1(10, 5));

	}

	private static double div(int i, int j) {
		if(j == 0) {
			System.out.println("나누기연산은 0으로 나눌 수가 없습니다!");
			return 0;
		}
		return i / j;
	}

}

interface MyInterface {
	int method1(int x, int y);
}
package com.lec.ex13_static;

public class CalculatorMain {

	public static void main(String[] args) {
		
		System.out.println("정적멤버 PI = " + Calculator.PI);
		System.out.println("정적멤버 add = " + Calculator.add(10,10));
		System.out.println();
		
		Calculator cal1 = new Calculator();
		System.out.println("인스턴스멤버 pi = " + cal1.pi);
		System.out.println("인스턴스멤버 mul = " + cal1.mul(10,10));
		System.out.println("객체에서 정적멤버접근 PI = " + cal1.PI);
		System.out.println("객체에서 정적멤버접근 add = " + cal1.add(10,10));
		
		// 객체에서 정적멤버를 접근이 가능하지만
		// 정적멤버(static)는 클래스로 접근하는 것을 권장한다.
		
		// xxx(); // static멤버인 main에서 객체멤버인 xxx() 호출불가
		CalculatorMain cm = new CalculatorMain();
		cm.xxx();
		yyy();
	}

	public void xxx() {}
	public static void yyy() {}

}

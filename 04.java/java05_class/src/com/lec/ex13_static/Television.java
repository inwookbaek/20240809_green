package com.lec.ex13_static;

/*
	정적(초기화)블럭
	
	정적초기화 블럭은 클래스가 메모리로 로딩일 될 때 자동을 실행되는 블걱이다.
	정적블럭은 클래스내부에 여러개를 선언할 수 있다.
	정적블럭은 클래스가 메모리로 로딩될 때 선언된 순서로 실행된다.
*/
public class Television {	
	
	static String company = "LGE";
	static String model = "OLED";
	static String info;  // LGE-OLED란 정보를 정적블럭을 이용해서 선언
	int year; // 객체멤버
	
	
	static {
		info = company + "-" + model;
	}
	static {
		// 정적블럭에서 객체멤버인 year에 접근 불가
		// year = 2024;
	}
	static {
		System.out.println("3번째 초기화 블럭입니다!");
	}
	static {
		System.out.println("4번째 초기화 블럭입니다!");		
	}
	static {}
	static {}
	static {}
	static {}
}

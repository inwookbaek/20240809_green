package com.lec.ex06_class;

public class Korean extends Adam {

	String nation = "대한민국";
	
	@Override
	void speak() {
		System.out.println("한국어로 말을 합니다!");
	}
	
	@Override
	void move() {
		System.out.println("프로그램을 개발합니다!");
	}
}

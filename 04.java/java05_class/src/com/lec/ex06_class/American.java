package com.lec.ex06_class;

public class American extends Adam {

	String nation = "미국";
	
	@Override
	void speak() {
		System.out.println("영어로 말을 합니다!");
	}
	
	@Override
	void move() {
		System.out.println("햄버거를 만듭니다!");
	}
	
}

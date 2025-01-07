package com.lec.ex07_class;

public class American extends Human {

	@Override
	void speak() {
		System.out.println("영어로 말을 합니다!");
	}
	
	@Override
	void move() {
		System.out.println("햄버거를 만듭니다!");
	}

}

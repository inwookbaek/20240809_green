package com.lec.ex08_class;

public class American implements Human {

	@Override
	public void speak() {
		System.out.println("영어로 말을 합니다!");
	}
	
	@Override
	public void move() {
		System.out.println("햄버거를 만듭니다!");
	}

	@Override
	public void think() {
		System.out.println("영어로 생각을 합니다!");
	}

}

package com.lec.ex01_inheritance.ex02;

public class Korean extends Adam {

	String ssn;
	
	@Override
	public void eat() {
		System.out.println("밥을 먹습니다!");
	}
	
	@Override
	public void speak() {
		System.out.println("한국어로 말을 합니다!");
	}
	
	@Override
	public void move() {
		System.out.println("프로그램을 개발합니다!");
	}
	
	void makeBaby() {
		if(this.gender == "남자") {
			System.out.println("육아를 함께합니다!");
		} else {
			System.out.println("아이를 낳습니다!");
		}
	}
}

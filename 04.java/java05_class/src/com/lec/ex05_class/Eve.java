package com.lec.ex05_class;

public class Eve extends Human {

	public Eve(String name, String gender, int age) {
		super(name, gender, age);
	}

	@Override
	void move() {
		System.out.println("음식을 만듭니다!!");
	}
	
	void makeBaby() {
		System.out.println("아이를 낳습니다!");
	}	
	
}

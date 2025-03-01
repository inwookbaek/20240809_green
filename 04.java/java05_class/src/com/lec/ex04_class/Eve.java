package com.lec.ex04_class;

public class Eve {
	
	String name = "이브";
	String gender = "여자";
	int age = 9999;
	
	void speak() {
		System.out.println("말을 합니다!");
	}	
	
	void move() {
		System.out.println("음식을 만듭니다!");
	}

	void makeBaby() {
		System.out.println("아이를 낳습니다!");
	}
	
	@Override
	public String toString() {
		return "Eve [name=" + name + ", gender=" + gender + ", age=" + age + "]";
	}
	
	
	
}

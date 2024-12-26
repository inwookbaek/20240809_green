package com.lec.ex08_class;

public class Korean implements Human {

	String name;
	String gender;
	int age;
	
	@Override
	public void speak() {
		System.out.println("한국어로 말을 합니다!");
	}
	
	@Override
	public void move() {
		System.out.println("프로그램을 개발합니다!");
	}
	@Override
	public void think() {
		System.out.println("한국어로 생각을 합니다!");		
	}

}

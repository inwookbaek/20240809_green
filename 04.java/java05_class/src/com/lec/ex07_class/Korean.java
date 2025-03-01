package com.lec.ex07_class;

// 추상클래스를 상속받는 자식클래스는
// 추상클래스에서 선언된 추상메서드를 반드시 재정의해야 한다.
public class Korean extends Human {

	@Override
	void speak() {
		System.out.println("한국어로 말을 합니다!");
	}
	
	@Override
	void move() {
		System.out.println("프로그램을 개발합니다!");
	}

	@Override
	public String toString() {
		return "Korean [nation=" + nation + ", name=" + name + ", gender=" + gender + ", age=" + age + "]";
	}

	// 1588-4141
	
}

package com.lec.ex07_class;

// 1. 추상메서드를 갖는 클래스는 반드시 추상클래스이어야 한다.
// 2. class를 abstract로 선언해야 한다.
// 3. 추상클래스는 추상메서드와 일반메서드를 동시에 선언할 수 있다.
// 4. 추상클래스는 객체를 생성할 수 없다.
public abstract class Human {
	
	String nation = "하늘나라";
	String name;
	String gender;
	int age;

	// 추상메서드 - 실행블럭이 없는 메서드
	abstract void speak(); 
	abstract void move();
	
	// 일반메서드
	void think() {
		System.out.println("사람은 생각을 합니다!!");
	}
	
}

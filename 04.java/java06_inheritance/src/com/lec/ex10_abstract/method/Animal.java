package com.lec.ex10_abstract.method;

public abstract class Animal {

	void breath() {
		System.out.println("숨을 쉰다!!!");
	}
	
//	void sound() {
//		System.out.println("소리를 낸다!!");
//	};
	
	// 추상메서드는 자식클래스에서 반드시 구현해야 한다.
	// 또한, 추상메서드를 정의한 클래스는 반드시 추상클래스로 
	// 선언해야 한다.
	abstract void sound();
}

class Dog extends Animal {
	@Override
	void sound() {
		System.out.println("멍멍하고 소리를 낸다!!");
	}
}
class Cat extends Animal {
	@Override
	void sound() {
		System.out.println("야옹하고 소리를 낸다!!");
	}
}
class Bird extends Animal {
	@Override
	void sound() {
		System.out.println("짹짹하고 소리를 낸다!!");
	}
}

class Tiger extends Animal {

	@Override
	void sound() {
		System.out.println("어흥하고 소리를 낸다!!");		
	}
}

class Lion extends Animal {

	@Override
	void sound() {
		System.out.println("사자가 어흥하고 소리를 낸다!!");		
	}	
}


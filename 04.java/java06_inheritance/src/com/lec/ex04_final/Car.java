package com.lec.ex04_final;

public class Car {

	public int speed;
	public void speedUp() { speed += 1; }
	
	// final로 지정된 메서드는 자식메서드에서 재정의를 할 수 없다.
	public final void stop() { speed = 0 ; }
}

class SuperCar extends Car {
	
	@Override public void speedUp() { speed += 10; }
	
	// final로 선언된 부모메서드는 재정읳 할 수 없다.
	// @Override public void stop() {}
}
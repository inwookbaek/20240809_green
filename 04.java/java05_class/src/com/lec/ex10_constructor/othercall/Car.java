package com.lec.ex10_constructor.othercall;

public class Car {

	// 1. 필드
	String company = "포르쉐";
	String model;
	String color;
	int maxSpeed;
	
	// 2. 생성자
	// this로 다른 시그니처의 생성자를 호출
	public Car() {
		this(null, null, 0);
		
	}
	
	Car(String model) {
		this(model, null, 0);
	}
	
	Car(int maxSpeed) {
		this(null, null, maxSpeed);
	}
	
	public Car(String model, int maxSpeed) {
		this(model, null, maxSpeed);
	}

	public Car(String model, String color, int maxSpeed) {
		this.model = model;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}
	
	// 3. 메서드
	@Override
	public String toString() {
		return "Car [model=" + model + ", color=" + color + ", maxSpeed=" + maxSpeed + "]";
	}
}

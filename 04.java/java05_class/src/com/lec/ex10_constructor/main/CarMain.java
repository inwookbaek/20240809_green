package com.lec.ex10_constructor.main;

public class CarMain {

	public static void main(String[] args) {
		
		Car car1 = new Car();   // 기본생성자호출
		Car car2 = new Car(3000, "빨강");
		Car car3 = new Car("빨강", 3000);
	}

}

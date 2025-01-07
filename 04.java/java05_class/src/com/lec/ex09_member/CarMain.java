package com.lec.ex09_member;

public class CarMain {

	public static void main(String[] args) {
		Car car1 = new Car();
		Car car2 = new Car("S500");
		Car car3 = new Car(500);
		Car car4 = new Car(400, 250);
		Car car5 = new Car("빨강", 250);
		System.out.println("car5의 속성 = " + car5.company + ", " + car5.color  + ", " + car5.maxSpeed );

		Car car6 = new Car("S500", "파랑", 500, 300);
		Car car7 = new Car("파랑", "S500",  500, 300);
		Car car8 = new Car(500, "하양", "S400", 300);
	}

}

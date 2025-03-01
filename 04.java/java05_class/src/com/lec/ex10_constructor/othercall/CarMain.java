package com.lec.ex10_constructor.othercall;

public class CarMain {

	public static void main(String[] args) {
		Car car1 = new Car();
		Car car2 = new Car("911카레라");
		Car car3 = new Car(300);
		Car car4 = new Car("911카레라", 300);
		Car car5 = new Car("911카레라", "빨강", 300);

	}

}

package com.lec.ex18_getset;

public class CarMain {

	public static void main(String[] args) {
		
		Car car = new Car();
		// Car의 속성들은 은닉화되었기 때문에 접근불가
		// car.speed = 100;
		// car.stop = ture;
		
		// 은닉화된 속성에 접근방법 setter or getter
		car.setSpeed(30);
		car.setStop(false);
		
		int speed = car.getSpeed();
		System.out.println("현재 속도는 " + speed + "km/h 입니다!");
		
		boolean stop = car.isStop();
		System.out.println("현재 자동차 운행은 " + stop + " 입니다!");

	}

}

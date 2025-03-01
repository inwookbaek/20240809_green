package com.lec.ex12_instance;

/*
	클래스(~.class)멤버와 인스턴스(객체)멤버
*/
public class Car {

	// String company = "포르쉐"; // 인스턴스(객체)멤버
	static String company = "포르쉐"; // 클래스(~.class)멤버
	String model;
	int speed;
	
	@Override
	public String toString() {
		return "Car [model=" + model + ", speed=" + speed + "]";
	}
	
	public static void xxx() {
		System.out.println("xxx메서드가 호출되었습니다!");
	}
}

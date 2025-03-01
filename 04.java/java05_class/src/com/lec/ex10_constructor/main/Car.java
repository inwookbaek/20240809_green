package com.lec.ex10_constructor.main;

public class Car {
	// 생성자
	// 기본생성자 이외의 다른 생성자가 정의가 되어 있다면
	// 기본생성자는 자동으로 추가되지 않기 때문에 기본생성자를
	// 사용하려면 별도로 정의해야 한다.
	public Car() {}
	Car(int cc, String color) {}
	public Car(String color, int cc) {};

}

package com.lec.ex10_abstract._class;

public class SmartPhoneMain {

	public static void main(String[] args) {
		
		// 추상클래스는 객체를 생성할 수 없다.
		// 추상클래스는 오직 상속만 할 수 있다.
		// Phone phone = new Phone("소향");
		
		SmartPhone smartPhone = new SmartPhone("소향");
		System.out.println("스마트폰의 소유자 = " + smartPhone.owner);
		smartPhone.powerOn();
		smartPhone.search("http://www.google.com");
		smartPhone.powerOff();
	}

}

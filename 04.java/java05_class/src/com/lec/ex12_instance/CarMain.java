package com.lec.ex12_instance;

// import com.lec.ex12_instance.Car;

public class CarMain {

	public static void main(String[] args) {

		System.out.println("자동차회사 = " + Car.company);  // 클래스멤버
		// System.out.println("자동차모델 = " + Car.model); // 인스턴스멤버
		
		Car car1 = new Car();
		car1.model = "911카레라";
		car1.speed = 300;
		System.out.println("자동차회사 = " + car1.company);
		System.out.println(car1.toString());
		System.out.println(car1.model);
		System.out.println();
		
		Car car2 = new Car();
		car2.model = "900카레라";
		car2.speed = 500;		
		System.out.println("자동차회사 = " + car2.company);
		System.out.println(car2.toString());
		System.out.println();
		
		Car.company = "벤츠";
		System.out.println("자동차회사 = " + Car.company);
		System.out.println(car1.company);
		System.out.println(car2.company);
		System.out.println();
		
		Car.xxx();
		car1.xxx();
		System.out.println();
		
		aaa();
		
		
	}
	
	static void aaa() {
		System.out.println("aaa메서드가 호출되었습니다!");
	}

}

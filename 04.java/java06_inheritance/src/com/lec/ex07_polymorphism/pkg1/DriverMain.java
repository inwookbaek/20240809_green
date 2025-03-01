package com.lec.ex07_polymorphism.pkg1;

public class DriverMain {

	public static void main(String[] args) {
		
		// hong은 1종, 택시, 대형버스 라이센스가 있다고 가정
		// hong은 복수의 차량을 운전할 수 있다 즉, 객체의 다형성을 
		// 구현할 수 있다.
		Driver hong = new Driver();
		hong.drive();
		hong.drive(new Vehicle());
		hong.drive(new Taxi());
		hong.drive(new Bus());		
		hong.drive(new 자전거());		
		hong.drive(new 퀵보드());		
		hong.drive(new 오토바이());		
		hong.drive(new 비행기());		
	}

}

class Driver {
	
	public void drive() {
		System.out.println("차량을 운전할 수 있습니다.");
	}
	
	public void drive(Vehicle vehicle) {
		vehicle.run();
	}
	
	public void drive(Taxi taxi) {
		taxi.run();
	}
	
	public void drive(Bus bus) {
		bus.run();
	}
	
	public void drive(자전거 자전거) {
		자전거.run();
	}
	
	public void drive(퀵보드 퀵보드) {
		퀵보드.run();
	}
	
	public void drive(오토바이	오토바이) {
		오토바이.run();
	}
	
	public void drive(비행기 비행기) {
		비행기.run();
	}
}

class Vehicle {
	void run() {
		System.out.println("승용차를 운전합니다!!!");
	}
}

class Taxi {
	void run() {
		System.out.println("택시를 운전합니다!");
	}
}

class Bus {
	void run() {
		System.out.println("버스를 운전합니다!!");
	}
}

class 자전거 {
	void run() {
		System.out.println("자전거를 탑니다!!");
	}
}

class 퀵보드 {
	void run() {
		System.out.println("퀵보드를 탑니다!!");
	}
}

class 오토바이 {
	void run() {
		System.out.println("오토바이를 탑니다!!");
	}
}

class 비행기 {
	void run() {
		System.out.println("비행기를 조정합니다!!");
	}
}

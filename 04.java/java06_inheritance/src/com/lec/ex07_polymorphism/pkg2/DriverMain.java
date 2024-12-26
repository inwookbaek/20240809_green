package com.lec.ex07_polymorphism.pkg2;

public class DriverMain {

	public static void main(String[] args) {
		
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
		System.out.println(vehicle.getClass().getName());
	}
}

class Vehicle {
	void run() {
		System.out.print("승용차를 운전합니다!!! -> ");
	}
}

class Taxi extends Vehicle {
	@Override
	void run() {
		System.out.print("택시를 운전합니다!!! -> ");
	}
}

class Bus extends Vehicle {
	@Override
	void run() {
		System.out.print("버스를 운전합니다!!! -> ");
	}
}

class 자전거 extends Vehicle {
	@Override
	void run() {
		System.out.print("자전거를 탑니다!!! -> ");
	}
}

class 퀵보드 extends Vehicle {
	@Override
	void run() {
		System.out.print("퀵보드를 탑니다!!! -> ");
	}
}

class 오토바이 extends Vehicle {
	@Override
	void run() {
		System.out.print("오토바이를 탑니다!!! -> ");
	}
}

class 비행기 extends Vehicle {
	@Override
	void run() {
		System.out.print("비행기를 조정합니다!!! -> ");
	}
}
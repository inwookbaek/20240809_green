package com.lec.ex03_method;

public class ComputerMain {

	public static void main(String[] args) {
		
		int radius = 10;
		System.out.println("반지름 10의 원의 넓이 = " + Math.PI * radius * radius);
		System.out.println();
		
		Calculator cal = new Calculator();
		System.out.println("원의 넓이 = " + cal.areaCircle(10));
		System.out.println();
		
		Computer com = new Computer();
		System.out.println("원의 넓이 = " + com.areaCircle(10));

	}

}

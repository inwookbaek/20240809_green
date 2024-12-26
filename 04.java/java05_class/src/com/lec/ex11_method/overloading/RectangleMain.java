package com.lec.ex11_method.overloading;

public class RectangleMain {

	public static void main(String[] args) {
		
		Rectangle ra = new Rectangle();
		
		int result1 = ra.areaRectangle(10);
		int result2 = ra.areaRectangle(10, 20);
		
		System.out.println("정사각형의 넓이 = " + result1);
		System.out.println("직사각형의 넓이 = " + result2);

	}

}

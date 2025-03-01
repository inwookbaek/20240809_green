package com.lec.ex11_method.overloading;

public class Rectangle {

	// 1. 정사각형
	int areaRectangle(int width) {
		return width*width;
	}
	
	// 2. 직사각형 - overloading
	int areaRectangle(int width, int height) {
		return width * height;
	}
}

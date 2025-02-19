package com.lec.ex11_method.declare;

public class Computer {

	int add(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j) {
		return a+b+c+d+e+f+g+h+i+j;
	}
	
	int add(int a, int b, int c) {
		return a+b+c;
	}
	
	int add(int a, int b, int c, int d, int e) {
		return a+b+c+d+e;
	}
	
	// int[]처럼 배열의 의미를 갖는 매개변수형태
	// int...x = int[]
	int add(int[] x) {
		int sum = 0;
		for(int i=0;i<x.length;i++) {
			sum += x[i];
		}
		return sum;
	}
	
	int add1(int...arr) {
		int sum = 0;
		for(int x:arr) {
			sum += x;
		}
		return sum;
	}
}











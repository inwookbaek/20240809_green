package com.lec.ex08_Wrapper;

public class WrapperMian3 {

	public static void main(String[] args) {
		// 포장객체의 값을 파싱하기
		
		// 1. boxing
		int val1 = Integer.valueOf("100");
		
		// 2. parsing
		int val2 = Integer.parseInt("100");
		Double val3 = Double.parseDouble("3.141592");
		Double val4 = Double.parseDouble("3.141592d");
		Float val5 = Float.parseFloat("3.141592d");
		boolean val6 = Boolean.parseBoolean("true");
		boolean val7 = Boolean.parseBoolean("false");
		boolean val8 = Boolean.parseBoolean("1");
		boolean val9 = Boolean.parseBoolean("0");
		
		System.out.println("val1 = " + val1);
		System.out.println("val2 = " + val2);
		System.out.println("val3 = " + val3);
		System.out.println("val4 = " + val4);
		System.out.println("val5 = " + val5);
		System.out.println("val6 = " + val6);
		System.out.println("val7 = " + val7);
		System.out.println("val8 = " + val8);
		System.out.println("val9 = " + val9);


	}

}

package com.lec.ex01_object.ex03_toString;

public class SmartPhoneMain {

	public static void main(String[] args) {
		
		Object obj1 = new Object();
		System.out.println("obj1.toString() -> " + obj1.toString() );

		SmartPhone sp1 = new SmartPhone("애플", "아이폰16");
		SmartPhone sp2 = new SmartPhone("삼성", "겔럭시s25");
		System.out.println("sp1.toString() -> " + sp1.toString() );
		System.out.println("sp2.toString() -> " + sp2.toString() );
	}

}

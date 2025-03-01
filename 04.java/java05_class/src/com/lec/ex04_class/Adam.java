package com.lec.ex04_class;

public class Adam {
	
	String name = "아담";
	String gender = "남자";
	int age = 10000;
	
	void speak() {
		System.out.println("말을 합니다!");
	}
	
//	void move() {
//		System.out.println("사냥을 합니다!");
//	}
	
	String move() {
		// return문은 한 개만 존재해야 된다.
		// return 값의 데이터타입은 일치해야 한다.
		// return "";
		// return 100;		
//		if(true) {
//			return "";		
//		}
		return "사냥을 합니다!";	
	}

	@Override
	public String toString() {
		return "Adam [name=" + name + ", gender=" + gender + ", age=" + age + "]";
	}	
}

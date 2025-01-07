package com.lec.ex08_class;

public class HumanMain {

	public static void main(String[] args) {
		// interface(인터페이스) 
		// Human human = new Human(); // interface 객체생성불가
		
		Korean kim = new Korean();
		// 인터페이스의 속성은 상수이기 때문에
		// 변경할 수 없다.
		// kim.NATION = "대한민국";
		// kim.NAME = "김씨";
		kim.name = "김씨";
		kim.gender = "남자";
		kim.speak();
		kim.move();
		kim.think();
		System.out.println();
		
		American steve = new American();
		steve.speak();
		steve.move();
		steve.think();	
		
	}

}

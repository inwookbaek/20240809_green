package com.lec.ex07_class;

public class HumanMain {

	public static void main(String[] args) {
		
		// 추상화 - 추상클래스는 객체화 할 수 없다.
		// 즉, 추상클래스는 상속하는 역할만 수행한다.
		// Human human = new Human();
		
		Korean kim = new Korean();
		kim.nation = "대한민국";
		kim.name = "김씨아저씨";
		kim.gender = "남자";
		kim.age = 25;
		kim.speak();
		kim.move();
		kim.think();
		System.out.println(kim.toString());
		System.out.println();
		
		American esther = new American();
		esther.nation = "미국";
		esther.name = "에스더";
		esther.gender = "여자";
		esther.age = 27;
		esther.speak();
		esther.move();
		esther.think();
		System.out.println(esther.toString());
		System.out.println();		
	}
}

package com.lec.ex05_class;

public class HumanMain {

	public static void main(String[] args) {
		// 상속(1)
		Human human1 = new Human("인간", "미정", 0);
		System.out.println("human1 = " + human1.toString());
		System.out.println();
		
		Adam adam = new Adam("아담", "남자", 1000);
		System.out.println("adam = " + adam.toString());
		adam.speak();
		adam.move();
		System.out.println();
		
		Eve eve = new Eve("이브", "여자", 9999);
		System.out.println("eve = " + eve.toString());
		eve.speak();
		eve.move();
		eve.makeBaby();
		
	}

}

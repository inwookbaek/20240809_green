package com.lec.ex01_inheritance.ex02;

public class HumanMain {

	public static void main(String[] args) {
		
		Human human = new Human();
		System.out.println(human.toString());
		human.speak();
		human.eat();
		human.move();
		System.out.println();
		
		Adam adam = new Adam();
		adam.name = "아담";
		adam.gender = "남자";
		adam.age = 10000;
		System.out.println(adam.toString());
		adam.speak();
		adam.eat();
		adam.move();
		System.out.println();		
		
		Eve eve = new Eve();
		eve.name = "이브";
		eve.gender = "여여자";
		eve.age = 9999;
		System.out.println(eve.toString());
		eve.speak();
		eve.eat();
		eve.move();
		System.out.println();
		
		Korean hong = new Korean();
		hong.name = "홍길순";
		hong.gender = "남자";
		hong.age = 1000;
		System.out.println(hong.toString());
		hong.speak();
		hong.eat();
		hong.move();
		hong.makeBaby();
		System.out.println();			
		
		American steve = new American();
		steve.name = "스티브";
		steve.gender = "남자";
		steve.age = 27;
		System.out.println(steve.toString());
		steve.speak();
		steve.eat();
		steve.move();
		System.out.println();			
	}

}

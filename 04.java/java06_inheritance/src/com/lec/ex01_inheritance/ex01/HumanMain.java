package com.lec.ex01_inheritance.ex01;

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
	}

}

package com.lec.ex01_class;

public class HumanMain {

	public static void main(String[] args) {
		
		String name1 = new String();        // 기본생성자
		String name2 = new String("소향");  // 기본생성자가 아님
		
		Human human1 = new Human();
		Human human2 = new Human(43);
		Human human3 = new Human("소향", 43);
		Human human4 = new Human(43, "소향");
		Human human5 = new Human("소향", "F", 43, "680405-2234567");
		
		System.out.println("human1.hashCode() = " + human1.hashCode());
		System.out.println("human2.hashCode() = " + human2.hashCode());
		System.out.println("human3.hashCode() = " + human3.hashCode());
		System.out.println("human4.hashCode() = " + human4.hashCode());
		System.out.println("human5.hashCode() = " + human5.hashCode());
		System.out.println();
	
		System.out.println("human1.name = " + human1.name);
		System.out.println("human2.name = " + human2.name);
		System.out.println("human3.name = " + human3.name + ", " + human3.age);
		System.out.println("human4.name = " + human4.name + ", " + human4.age);
		System.out.println("human5.name = " + human5.name + ", " + human5.gender
				+ ", " + human5.age + ", " + human5.ssn);
		System.out.println();
		
		human1.think();
		human1.eat();
		human1.speak();
		human1.move();
		System.out.println();
		
		human2.think();
		human3.think();
		human4.think();
		human5.think();
		
	}

}

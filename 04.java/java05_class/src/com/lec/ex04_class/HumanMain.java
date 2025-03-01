package com.lec.ex04_class;

public class HumanMain {

	public static void main(String[] args) {
		
		// 메서드 : return문 and 오버라이딩
		
		Adam adam = new Adam();
		System.out.println("Adam = " + adam.name 
				+ " , " +  adam.gender + " , " +  adam.age);
		adam.speak();
		String message = adam.move();
		System.out.println(message);
		System.out.println(adam.toString());
		System.out.println();		
		
		Eve eve = new Eve();
		System.out.println("Eve = " + eve.name 
				+ " , " +  eve.gender + " , " +  eve.age);
		eve.speak();
		eve.move();
		eve.makeBaby();
		System.out.println(eve.toString());		

	}

}

package com.lec.ex02_class;

public class HumanMain {

	public static void main(String[] args) {
		
		Human human1 = new Human();
		Human human2 = new Human("소향");
		Human human3 = new Human("소향", "여자", 45);
		
		System.out.println("human1 =  " + human1.nation + ", " + human1.name);
		System.out.println("human2 =  " + human2.nation + ", " + human2.name);
		System.out.println("human3 =  " + human3.nation + ", " + human3.name
				+ ", " + human3.gender + ", " + human3.age);
		System.out.println();
		
		System.out.println("human3 =  " + human3.toString()
		   	+ ", " + human3.hashCode());
	}

}

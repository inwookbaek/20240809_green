package com.lec.ex06_class;

public class HumanMain {

	public static void main(String[] args) {

	Adam adam = new Adam();
	Eve eve = new Eve();
	
	adam.gender = "Male";
	eve.gender = "Female";
	
	Korean kim = new Korean();
	American steve = new American();
	
	System.out.println("kim = " + kim.nation);
	System.out.println("kim = " + kim.gender);
	kim.speak();
	kim.move();
	System.out.println();
	
	System.out.println("steve = " + steve.nation);
	System.out.println("steve = " + steve.gender);
	steve.speak();
	steve.move();
	}

}

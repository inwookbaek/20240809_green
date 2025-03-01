package com.lec.ex10_abstract.method;

public class AnimalMain {

	public static void main(String[] args) {
		
		// Animal animal = new Animal();
		
		Animal dog = new Dog();
		dog.breath();
		dog.sound();
		System.out.println();
		
		Animal cat = new Cat();
		cat.breath();
		cat.sound();
		System.out.println();
		
		Animal bird = new Bird();
		bird.breath();
		bird.sound();
		System.out.println();
		
		Animal tiger = new Tiger();
		tiger.breath();
		tiger.sound();
		System.out.println();
		
		Animal 사자 = new Lion();
		사자.breath();
		사자.sound();
		System.out.println();
		
	}

}

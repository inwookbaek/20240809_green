package com.lec.ex01_runtime;

public class ClassCastExceptionMain {

	public static void main(String[] args) {
		
		Dog dog = new Dog();
		changeDog(dog);
		
		// java.lang.ClassCastException:
		Cat cat = new Cat();
		changeDog(cat);		
	}

	private static void changeDog(Animal animal) {
		System.out.println("class type = " + animal.getClass().getName());
		Dog dog = (Dog) animal;
		System.out.println("dog.toString() = " + dog);
	}

}

class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}

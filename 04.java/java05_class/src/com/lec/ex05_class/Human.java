package com.lec.ex05_class;

public class Human {

	String nation = "하늘나라";
	String name;
	String gender;
	int age;
	
	public Human() {}
	public Human(String name, String gender, int age) {
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	void speak() {
		System.out.println("천국말을 합니다!");
	}
	
	void move() {
		System.out.println("사냥을 합니다!");
	}
	
	@Override
	public String toString() {
		return this.nation;
	}
}

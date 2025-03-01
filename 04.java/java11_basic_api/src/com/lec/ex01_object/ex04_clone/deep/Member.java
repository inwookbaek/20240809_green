package com.lec.ex01_object.ex04_clone.deep;

import java.util.Arrays;

public class Member implements Cloneable {
	
	public String name;
	public int age;
	public int[] scores;
	public Car car;;
	
	public Member(String name, int age, int[] scores, Car car) {
		super();
		this.name = name;
		this.age = age;
		this.scores = scores;
		this.car = car;
	}
	
	public Member getMember() {
		
		Member member = null;
		
		try {
			member = (Member) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Member member = null;
		
		// 1. 얕은복제 : name, age, scores, car
		member = (Member) super.clone(); // Object.clone()호출, 즉 얕은복제
		
		// 2. 깊은복제 : scores, car
		member.scores = Arrays.copyOf(this.scores, this.scores.length);
		member.car = new Car(this.car.model);
		
		// return super.clone();
		return member;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", scores=" + Arrays.toString(scores) + ", car=" + car + "]";
	}
	
}

class Car {
	public String model;
	
	public Car(String model) {
		this.model = model;
	}
	
	@Override
	public String toString() {
		return "Car [model=" + model + "]";
	}
	
}
package com.lec.ex01_inheritance.ex02;

public class American extends Adam {

	String socNumber;
	
	@Override
	public void eat() {
		// 최종적으로 eat는 Adam > Human의 eat가 실행
		super.eat();
	}
	
	@Override
	public void speak() {
		System.out.println("영어로 말을 합니다!");
	}
	
	@Override
	public void move() {
		System.out.println("주식을 중개합니다!");
	}
	
	@Override
	public String toString() {
		return "나는 미국사람입니다! 내 이름은 " + this.name + " 입니다!";
	}
}

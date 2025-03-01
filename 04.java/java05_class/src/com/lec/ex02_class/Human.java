package com.lec.ex02_class;

import java.util.Objects;

public class Human {

	// 1. 속성
	String nation = "대한민국";
	String name;
	String gender;
	int age;
	
	// 2. 생성자
	// 생성자의 오버로딩
	// 객체의 다형성 - 시그니처가 다른 생성자를 여러개 선언
	// 객체생성시에 다양한 형태의 객체를 만들 수 있게 하는
	// 방법을 말한다.
	Human() {}
	Human(String name) {
		this.name = name;
	}
	public Human(String name, String gender, int age) {
		super();  // 최상위부모객체인 Object의 생성자를 호출
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	public Human(int age) {
		this.age = age;
		// 자식객체가 생성되기 전에 부모객체가 생성되야 하기 때문에
		// super()는 부모객체를 먼저 생성해야 하기 때문에 자식생성자
		// 맨위(첫줄)에 선언되야 한다.
		// super() 즉 부모의 기본생성자를 생략하면 자동으로 생성된다.
		// super();  
	}
	public Human(String nation, String name, String gender, int age) {
		super();
		this.nation = nation;
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	
	public Human(String name, String gender, int age, String nation) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.nation = nation;
	}
	// 3. 메서드 오버라이딩
	// 오버라이딩이란 부모의 메서드를 자식에서 재정의하는 것을 말한다.
	// 즉, 상속받은 메서드를 자식에서 재정의 하는 것
	
//	public String toString() {
//		return this.name 
//				+ ", " + this.gender
//				+ ", " + this.age;
//	}
	
	// @애너테이션 - 메타정보(주석같은 의미)
	// @Override의미는 컴파일시에 부모메서드를 재정의했다는 의미
	@Override
	public String toString() {
		return "Human [nation=" + nation + ", name=" + name + ", gender=" + gender + ", age=" + age + "]";
	}
	
	@Override
	public int hashCode() {
		// return Objects.hash(age, gender, name, nation);
		return 10000000;
	}
	
}






















package com.lec.ex03_class;

public class Human {

	// 1. 필드
	String name;
	
	// 2. 생성자 - 오버로딩
	public Human() {
		super(); // new Object()와 동일
	}
	
	public Human(String name) {
		// super 부모, this 객체자기자신
		super();
		this.name = name;
	}
	
	// 3. 메서드 - 오버로딩
	// 메서드명은 동일하지만 시그니처가 다른 경우
	void speak() { System.out.println("말을 합니다!"); }
	void speak(String lang) {
		System.out.println(lang + "로 말을 합니다!");
	}
	void speak(int age, String lang) {
		System.out.println(String.format("나는 %d살이고 %s로 말을 합니다!", age, lang));
	}
	
	void speak(String lang, int age) {
		System.out.println(String.format("%s로 말을 하고 나이는 %d살 입니다!", lang, age));	
	}
}

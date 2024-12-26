package com.lec.ex08_class;

// 1. 인터페이스는 모든 메서드가 추상메서드로 선언해야 한다.
//    abstract를 생략해도 자동으로 abstract 메서드로 선언된다.
//    추상메서드는 접근제한자가 public으로 선언된다.
// 2. 인터페이스에 선언된 속성(필드)은 모두 상수로 선언해야 한다.
//    즉, final static을 생략해도 자동으로 finale static으로 선언된다.
//    관례에 따라서 상수는 대문자로 선언되고 반드시 초기화가 되어야 한다.
// 3. 인터페이스는 인스턴스화(객체화)를 할 수 없다
//    즉, 객체를 생성할 수 없다.
public interface Human {

	String NATION = "하늘나라"; // final static 생략
	final static String NAME = "";
	String gender = "";
	int age = 0;
	
	abstract void speak(); // public이 생략
	void move();  // public abstract가 생략
	void think();
}

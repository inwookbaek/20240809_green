package com.lec.ex02_supercall;

public class Human {
	String name;
	String ssn;
	
	public Human() {
		super();    // super() 는 Object의 기본생성자를 호출
	}

	public Human(String name, String ssn) {
		// super()가 생략, 왜냐하면 부모는 자식은 없기 때문에
		// 부모가 먼저 생성된 후에 자식이 생성되기 떄문이다.
		// 따라서 Java에서는 부모생성자(super)호출은 맨 첫 줄에
		// 정의가 되어야 한다.
		this.name = name;
		this.ssn = ssn;
		// super(); // 에러 : Constructor call must be the first statement in a constructor
	}
	
	
}

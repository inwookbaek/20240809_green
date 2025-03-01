package com.lec.ex14_singleton;

public class GetSet {

	public int num1 = 10;
	private String name;
	
	// num2는 private을 선언했기 때문에
	// 외부에서 접근불가, 내부는 접근 가능
	private int num2 = 20;
	
	
	// getter메서드
	public int getNum2() {
		return this.num2;
	}

	// setter메서드
	public void setNum2(int num) {
		this.num2 = num;
	}
	
	// 실습. name에 대한 getter/setter 만들기
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}

package com.lec.ex10_constructor.field;

public class KoreanMain {

	public static void main(String[] args) {
		Korean kim = new Korean("김씨", "951118-1234567");
		System.out.println(kim.toString());
		System.out.println(kim.result);
		
		System.out.println(kim.add(2000, 5000));
		// 필드의 속성이 변하는 것이 아니라 메서드만 호출한 것이다
		// 따라서, kim객체의 result속성은 변경되지 않는다. 
		System.out.println(kim.result);
		
		// result의 속성을 변경하려면
		kim.result = kim.add(2000, 5000);
		System.out.println(kim.result);
		
	}

}

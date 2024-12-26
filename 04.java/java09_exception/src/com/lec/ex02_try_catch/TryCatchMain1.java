package com.lec.ex02_try_catch;

public class TryCatchMain1 {

	public static void main(String[] args) {
		// 1. 일반예외
		// Class.forName(클래스이름)메서드는 해당클래스를 객체로 변환해 주는 메서드
		// 즉, new객체생성연산자와 동일한 기능을 한다. 만약에 해당클래스가 없을 경우
		// ClassNotFoundException예외가 발생이 된다.
		try {
			Class _class = Class.forName("java.lang.Stringxxxx");
			System.out.println(_class.getName());
		} catch (ClassNotFoundException e) {
			System.out.println("클래스를 찾지 못했습니다!!!");
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
		
		System.out.println("아주 아주 중요한 로직을 처리를 해야 합니다!!");
	}

}

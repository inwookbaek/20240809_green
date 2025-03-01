package com.lec.ex01_object.ex04_clone.shallow;

public class MemberMain {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		// 1. 원본객체
		Member om = new Member("hong", "홍길동", "12345", 1000, true);
		
		// 2. 복제객체 - 객체를 복제후 패스워드를 변경
		Member cm = om.getMember(); // 복제가 될까? Cloneable 구현여부에 따라

		System.out.println("원본(변경전) 패스워드 = " + om.password);
		cm.password = "abcde";
		System.out.println();
		
		System.out.println("[원본객체] " + om.toString());
		System.out.println("[복제객체] " + cm.toString());
		System.out.println();
		
		System.out.println("[원본객체.password hashCode] " + om.password.hashCode());
		System.out.println("[복제객체.password hashCode] " + cm.password.hashCode());
		System.out.println();
		
		System.out.println("[원본객체.scores hashCode] " + om.scores.hashCode());
		System.out.println("[복제객체.scores hashCode] " + cm.scores.hashCode());
		System.out.println();
		
		// 복제객체의 점수를 수정하면 결과는?
		cm.scores[0] = 100;
		System.out.println("[원본객체.scores[0] = " + om.scores[0]);
		System.out.println("[복제객체.scores[0] = " + cm.scores[0]);
		
	}

}

















package com.lec.ex01_object.ex04_clone.deep;

public class MemberMain {

	public static void main(String[] args) {
		
		// 1. 원본객체생성
		Member om = new Member("홍길동", 1000
				, new int[] {Integer.valueOf(90), 85, 95}
				, new Car("포르쉐 911 카레라"));
		
		// 2. 복제객체깊은복제
		Member cm = om.getMember();
		
		// 3. 객체수정전
		System.out.println("[원본객체] " + om.toString());
		System.out.println("[복제객체] " + cm.toString());
		System.out.println();
		
		System.out.println("[원본객체.scores, car의 hashCode] " + om.scores.hashCode() + ", " + om.car.hashCode());
		System.out.println("[복제객체.scores, car의 hashCode] " + cm.scores.hashCode() + ", " + cm.car.hashCode());
		System.out.println();		
		
		// 4. 복제객체의 점수를 수정하면 결과는?
		cm.scores[0] = 100;
		cm.car.model = "벤츠 s500";
		System.out.println("[원본객체] " + om.toString());
		System.out.println("[복제객체] " + cm.toString());

		
	}

}

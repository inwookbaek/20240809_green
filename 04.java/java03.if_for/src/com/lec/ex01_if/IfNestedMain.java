package com.lec.ex01_if;

public class IfNestedMain {

	public static void main(String[] args) {
		// 난수를 81~100사이의 난수를 발생시키기
		// Math.random()은 0 <= 난수 < 1 사이의 double타입의 실수 1개를 리턴
		System.out.println(Math.random()); // 0 <= 난수 < 1
		System.out.println(Math.random()*20); // 0 <= 난수 < 20
		System.out.println(Math.random()*20 + 81); // 81 <= 난수 < 100
		System.out.println();
		
		int score = ((int) Math.random()*20 + 81) + 1; // 81 <= 난수 <= 100
		
		// 중첩if문
		if(score>=90) {
			if(score>=95) {
				System.out.println("점수가 95보다 크거나 같습니다!");
				System.out.println(score + "는 A+학점입니다!");				
			} else {
				System.out.println("점수가 90보다 크거나 같습니다!");
				System.out.println(score + "는 A0학점입니다!");							
			}
		} else if(score>=80) {
			if(score>=85) {
				System.out.println("점수가 85보다 크거나 같습니다!");
				System.out.println(score + "는 B+학점입니다!");				
			} else {
				System.out.println("점수가 80보다 크거나 같습니다!");
				System.out.println(score + "는 B0학점입니다!");							
			}
		}
	}

}

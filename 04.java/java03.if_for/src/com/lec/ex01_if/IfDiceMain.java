package com.lec.ex01_if;

public class IfDiceMain {

	public static void main(String[] args) {
		// 실습. 주사위던지기 Math.random
		// "?번 주사위간 나왔습니다!!"를 출력하는 로직
		// if~else if~ else~;
		
		int num = (int) (Math.random()*6) + 1;
		
		if(num == 1) {
			System.out.println(num + "번 주사위가 나왔습니다!!");
		} else if(num == 2) {
			System.out.println(num + "번 주사위가 나왔습니다!!");			
		} else if(num == 3) {
			System.out.println(num + "번 주사위가 나왔습니다!!");			
		} else if(num == 4) {
			System.out.println(num + "번 주사위가 나왔습니다!!");			
		} else if(num == 5) {
			System.out.println(num + "번 주사위가 나왔습니다!!");			
		} else {
			System.out.println(num + "번 주사위가 나왔습니다!!");						
		}
	}

}

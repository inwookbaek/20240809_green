package com.lec.ex02_switch;

public class SwitchMain {

	public static void main(String[] args) {
		// 실습.주사위던지기
		// switch문으로 작성, break
		
		int num = (int) (Math.random()*6) + 1;
		switch(num) {
		case 1:
			System.out.println(num + "번 주사위가 나왔습니다!!");
			break;
		case 2:
			System.out.println(num + "번 주사위가 나왔습니다!!");
			break;
		case 3:
			System.out.println(num + "번 주사위가 나왔습니다!!");
			break;
		case 4:
			System.out.println(num + "번 주사위가 나왔습니다!!");
			break;
		case 5:
			System.out.println(num + "번 주사위가 나왔습니다!!");
			break;
//		case 6:
//			System.out.println(num + "번 주사위가 나왔습니다!!");
		default:
			System.out.println(num + "번 주사위가 나왔습니다!!");
		}

	}

}

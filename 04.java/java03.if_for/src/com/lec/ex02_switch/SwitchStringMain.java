package com.lec.ex02_switch;

public class SwitchStringMain {

	public static void main(String[] args) {
		// Java 1.6이전까지는 switch(조건)의 조건에는 정수타입만 가능
		// 그 이후버전 부터는 String도 가능하게 되었다.
		String position = "과장";
		
		switch(position) {
		case "부장":
			System.out.println("부장급여는 1000만원 입니다!");
			break;
		case "과장":
			System.out.println("과장급여는 800만원 입니다!");
			break;
		case "대리":
			System.out.println("대리급여는 600만원 입니다!");
			break;
		case "사원":
			System.out.println("사원급여는 400만원 입니다!");
			break;
		default:
			System.out.println("직급이 없습니다!!!");
		}

	}

}

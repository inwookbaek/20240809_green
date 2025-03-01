package com.lec.ex02_switch;

public class SwitchCharMain {

	public static void main(String[] args) {

		char grade = 'C';
		
		switch(grade) {
		case 'A':
		case 'a':
			System.out.println("A학점입니다!");
			break;
		case 'B':
		case 'b':
			System.out.println("B학점입니다!");
			break;
		default:
			System.out.println("학점을 입력하지 않았습니다! 학점을 입력하세요!!");
		}

	}

}

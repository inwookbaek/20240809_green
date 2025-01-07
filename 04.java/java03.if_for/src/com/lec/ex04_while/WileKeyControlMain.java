package com.lec.ex04_while;

import java.io.IOException;

public class WileKeyControlMain {

	public static void main(String[] args) throws IOException {
		// 외부에서 특정의 값을 입력받기 위해서는 여러가지 방법중에
		// System.in.read()메서드를 사용한다. 이 메서드는 키보드의
		// 입력을 대기하고 있다가 enter키가 입력된 후에 keyboard의
		// ASCII(예:A -> 65)값을 정수로 리턴한다.
		// Return Key=13, Enter Key=10, A=65, a=97, 0=48
		
		// Remote Controller로직 만들기
		// 1번키를 누르면 볼륨 Up, 2번은 볼륨, 3번키일경우 음소거 & stop
		boolean run = true;
		int keyCode = 0;
		int volumn = 0;
		
		while(run) {
			if(keyCode !=13 && keyCode != 10) {
				System.out.println("--------------------------------------------");
				System.out.println("1. 볼륨업    2. 볼륨다운    3. 음소거 & 종료");
				System.out.println("--------------------------------------------");
			}
			
			keyCode = System.in.read();
			// System.out.println("입력된 키코드 = " + keyCode);
			
			if (keyCode==49) {
				volumn++;
				System.out.println("현재 볼륨 = " + volumn);
			} else if(keyCode==50) {
				volumn--;
				System.out.println("현재 볼륨 = " + volumn);
			} else if(keyCode==51) {
				run = false;				
			}
		}
		 System.out.println("프로그램이 종료되었습니다!");
	}

}

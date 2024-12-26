package com.lec.ex01_create;

import java.awt.Toolkit;

public class BeepMain1 {

	public static void main(String[] args) throws InterruptedException {
		
		// 띵소리 5번 - Toolkit(스피커를 조정해주는 클래스)
		Toolkit toolkit = Toolkit.getDefaultToolkit(); // PC의 스피커자원을 가져오는 메서드 
		for(int i=0;i<5;i++) {
			toolkit.beep();
			Thread.sleep(1000); // 1000ms = 1 sec 정지
		}
		
		// 띵출력 5번
		for(int i=0;i<5;i++) {
			System.out.println("띵~~~");
			Thread.sleep(1000); // 1000ms = 1 sec 정지
		}
	}

}

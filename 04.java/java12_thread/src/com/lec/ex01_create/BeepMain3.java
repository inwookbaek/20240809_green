package com.lec.ex01_create;

import java.awt.Toolkit;

public class BeepMain3 {

	public static void main(String[] args) throws InterruptedException {
		// 1. 외부클래스(BeepTask2, Thread를 상속)
		// Thread thread1 = new BeepTask2();
		// thread1.start();
		
		// 2. 내부에 익명객체(Thread 클래스)
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				Toolkit toolkit = Toolkit.getDefaultToolkit(); // PC의 스피커자원을 가져오는 메서드 
				for(int i=0;i<5;i++) {
					toolkit.beep();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} // 1000ms = 1 sec 정지
				}
			}		
		};
		thread2.start();
		
		// 띵출력 5번
		for(int i=0;i<5;i++) {
			System.out.println("띵~~~");
			Thread.sleep(1000); // 1000ms = 1 sec 정지
		}	
		
	}

}

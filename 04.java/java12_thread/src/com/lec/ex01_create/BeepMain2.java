package com.lec.ex01_create;

import java.awt.Toolkit;

public class BeepMain2 {

	public static void main(String[] args) throws InterruptedException {
		// Thread를 만드는 방법
		// 1. 외부에 클래스 생성(BeepTask1, Runnable구현)
		// 띵소리 5번
//		Runnable beepTask = new BeepTask1();
//		Thread thread1 = new Thread(beepTask);
//		thread1.start();

		// 2. 내부에 익명구현객체구현(Runnable구현)
//		Thread threa2 = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				Toolkit toolkit = Toolkit.getDefaultToolkit(); // PC의 스피커자원을 가져오는 메서드 
//				for(int i=0;i<5;i++) {
//					toolkit.beep();
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					} // 1000ms = 1 sec 정지
//				}		
//			}
//		});
//		threa2.start();
		
		// 3. 람다(Lambda)식 -> JavaScript의 화살표함수와 유사
		Thread thread3 = new Thread(() -> {
			Toolkit toolkit = Toolkit.getDefaultToolkit(); // PC의 스피커자원을 가져오는 메서드 
			for(int i=0;i<5;i++) {
				toolkit.beep();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // 1000ms = 1 sec 정지
			}			
		}); 
		thread3.start();
		
		// 띵출력 5번
		for(int i=0;i<5;i++) {
			System.out.println("띵~~~");
			Thread.sleep(1000); // 1000ms = 1 sec 정지
		}		
	}

}

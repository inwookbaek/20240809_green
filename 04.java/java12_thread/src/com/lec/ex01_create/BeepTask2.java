package com.lec.ex01_create;

import java.awt.Toolkit;

public class BeepTask2 extends Thread {

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
}

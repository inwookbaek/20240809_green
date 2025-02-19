package com.lec.ex02_name;

public class ThreadB extends Thread {
	
	// 쓰레드이름 자동부여
	@Override
	public void run() {
		for(int i=0;i<2;i++) {
			System.out.println(super.getName() + "가 출력한 내용!!!");
		}
	}
}

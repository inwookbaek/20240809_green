package com.lec.ex18_getset;

public class Car {

	// 객체의 속성(메서드)을 은닉화(캡슐화)한다.
	private int speed = 0;
	private boolean stop;
	
	// 1. setter
	public void setSpeed(int speed) {
		
		if(speed > 100) {
			System.out.println(speed + "km/h [속도위반] 제한속도를 위반했습니다!");
			this.speed = 100;
		} else {
			if(speed < 30) {
				System.out.println(speed + "km/h [속도위반] 제한속도가 너무 느립니다!");
				this.speed = 30;
			} else {
				this.speed = speed;				
			}
		}
	}
	
	public void setStop(boolean stop) {
		if(this.speed <= 30) {
			System.out.println("자동차운행을 중지 합니다!");
			this.stop = true;
		} else {
			System.out.println("현재 속도는 " + this.speed + "km/h 입니다!");
			System.out.println("자동차를 정지하기에는 속도가 너무 빠릅니다!!");
			System.out.println("속도를 줄여 주세요!!!");
			this.stop = false;
		}
	}
	
	// 2. getter
	public int getSpeed() {
		return this.speed;
	}
	
	public boolean isStop() {
		return this.stop;
	}
}

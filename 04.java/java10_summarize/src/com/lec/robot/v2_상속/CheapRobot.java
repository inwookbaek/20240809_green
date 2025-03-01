package com.lec.robot.v2_상속;

public class CheapRobot extends Robot {

	public CheapRobot(String name, int qty) {
		super(name, qty);
	}
	
	public void actionFly() { System.out.println("날 수가 없습니다!!"); }
	public void actionMissile() { System.out.println("미사일을 쏠 수가 없습니다!!"); }
	public void actionSword() { System.out.println("공격용 검이 없습니다!!"); }

}

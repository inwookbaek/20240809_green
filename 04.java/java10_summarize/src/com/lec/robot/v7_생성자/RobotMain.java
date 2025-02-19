package com.lec.robot.v7_생성자;

import com.lec.robot.v4_인터페이스.impl.*;

public class RobotMain {

	public static void main(String[] args) {
		
		System.out.println("=== Robot V7.0 (생성자(초기화)) ===");
		System.out.println();
		
		Robot robot = null;
		
		robot = new Robot("Cheap", 2, new FlyYes(), new MissileYes(), new SwordWood());
		robot.makeRobot();
		
		robot = new Robot("Standard", 6, new FlyYes(), new MissileICBM(), new SwordLaser());
		robot.makeRobot();
		
		robot = new Robot("Super", 2, new FlyYes(), new MissileNuclear(), new UltraJjangSword());
		robot.makeRobot();
		
		robot = new Robot("UltraJjang", 2, new FlyNo(), new MissileNuclear(), new UltraJjangSword());
		robot.makeRobot();
	}

}

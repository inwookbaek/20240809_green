package com.lec.robot.v5_extends;

import com.lec.robot.v4_인터페이스.impl.*;

public class RobotMain {

	public static void main(String[] args) {
		
		System.out.println("=== Robot V5.0 (인터페이스, 상속, 자동형변환) ===");
		System.out.println();	
		
		Robot robot = null;
		
		robot = new CheapRobot("CheapRobot", 2);
		System.out.println(robot.toString());
		robot.shape();
		robot.actionWalk();
		robot.actionRun();
		robot.setFly(new FlyYes());
		robot.actionFly();
		robot.setMissile(new MissileYes());
		robot.actionMissile();
		robot.setSword(new SwordWood());
		robot.actionSword();
		System.out.println("-".repeat(50));		
		
		robot = new SuperRobot("SuperRobot", 6);
		System.out.println(robot.toString());
		robot.shape();
		robot.actionWalk();
		robot.actionRun();
		robot.setFly(new FlyYes());
		robot.actionFly();
		robot.setMissile(new MissileYes());
		robot.actionMissile();
		robot.setSword(new SwordLaser());
		robot.actionSword();
		System.out.println("-".repeat(50));		
		
		robot = new SuperRobot("SuperRobot", 2);
		System.out.println(robot.toString());
		robot.shape();
		robot.actionWalk();
		robot.actionRun();
		robot.setFly(new FlyYes());
		robot.actionFly();
		robot.setMissile(new MissileICBM());
		robot.actionMissile();
		robot.setSword(new UltraJjangSword());
		robot.actionSword();
		System.out.println("-".repeat(50));		
		
		robot = new UltraJjangRobot("UltraJjangRobot", 10);
		System.out.println(robot.toString());
		robot.shape();
		robot.actionWalk();
		robot.actionRun();
		robot.setFly(new FlyNo());
		robot.actionFly();
		robot.setMissile(new MissileNuclear());
		robot.actionMissile();
		robot.setSword(new UltraJjangSword());
		robot.actionSword();
		System.out.println("-".repeat(50));		
		
	}
}

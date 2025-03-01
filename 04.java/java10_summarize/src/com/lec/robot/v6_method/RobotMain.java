package com.lec.robot.v6_method;

import com.lec.robot.v4_인터페이스.impl.*;
import com.lec.robot.v4_인터페이스.inter.*;
import com.lec.robot.v5_extends.Robot;

public class RobotMain {

	public static void main(String[] args) {
		
		System.out.println("=== Robot V6.0 (Common Method) ===");
		System.out.println();	

		makeRobot(new Robot("CheapRobot", 2), new FlyYes(), new MissileYes(), new SwordWood());
		makeRobot(new Robot("StandardRobot", 6), new FlyYes(), new MissileYes(), new SwordLaser());
		makeRobot(new Robot("SuperRobot", 2), new FlyYes(), new MissileNuclear(), new SwordLaser());
		makeRobot(new Robot("UltraJjangRobot", 10), new FlyYes(), new MissileNuclear(), new UltraJjangSword());
	}

	private static void makeRobot(Robot robot, InterFly fly, InterMissile missile, InterSword sword) {
		System.out.println(robot.getClass());
		System.out.println(robot.toString());
		robot.shape();
		robot.actionWalk();
		robot.actionRun();
		robot.setFly(fly);
		robot.actionFly();
		robot.setMissile(missile);
		robot.actionMissile();
		robot.setSword(sword);
		robot.actionSword();
		System.out.println("-".repeat(50));			
	}
	
}

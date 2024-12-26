package com.lec.robot.v1_기본;

public class RobotMain {

	public static void main(String[] args) {
		
		System.out.println("=== Robot V1.0 (기본) ===");
		System.out.println();
		
		CheapRobot cheapRobot = new CheapRobot(2);
		System.out.println("[CheapRobot] " + cheapRobot.toString());
		cheapRobot.shape();
		cheapRobot.actionWalk();
		cheapRobot.actionRun();
		cheapRobot.actionFly();
		cheapRobot.actionMissile();
		cheapRobot.actionSword();
		System.out.println("-".repeat(50));
		
		StandardRobot standardRobot = new StandardRobot(6);
		System.out.println("[StandardRobot] " + standardRobot.toString());
		standardRobot.shape();
		standardRobot.actionWalk();
		standardRobot.actionRun();
		standardRobot.actionFly();
		standardRobot.actionMissile();
		standardRobot.actionSword();
		System.out.println("-".repeat(50));
		
		SuperRobot superRobot = new SuperRobot(2);
		System.out.println("[SuperRobot] " + superRobot.toString());
		superRobot.shape();
		superRobot.actionWalk();
		superRobot.actionRun();
		superRobot.actionFly();
		superRobot.actionMissile();
		superRobot.actionSword();
		System.out.println("-".repeat(50));
	}

}

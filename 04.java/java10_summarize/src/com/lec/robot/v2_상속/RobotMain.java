package com.lec.robot.v2_상속;


public class RobotMain {

	public static void main(String[] args) {
		
		System.out.println("=== Robot V2.0 (상속) ===");
		System.out.println();	
		
		CheapRobot cheapRobot = new CheapRobot("CheapRobot", 2);
		System.out.println(cheapRobot.toString());
		cheapRobot.shape();
		cheapRobot.actionWalk();
		cheapRobot.actionRun();
		cheapRobot.actionFly();
		cheapRobot.actionMissile();
		cheapRobot.actionSword();
		System.out.println("-".repeat(50));
		
		StandardRobot standardRobot = new StandardRobot("StandardRobot", 6);
		System.out.println(standardRobot.toString());
		standardRobot.shape();
		standardRobot.actionWalk();
		standardRobot.actionRun();
		standardRobot.actionFly();
		standardRobot.actionMissile();
		standardRobot.actionSword();
		System.out.println("-".repeat(50));
		
		SuperRobot superRobot = new SuperRobot("SuperRobot", 2);
		System.out.println(superRobot.toString());
		superRobot.shape();
		superRobot.actionWalk();
		superRobot.actionRun();
		superRobot.actionFly();
		superRobot.actionMissile();
		superRobot.actionSword();
		System.out.println("-".repeat(50));
	}
}

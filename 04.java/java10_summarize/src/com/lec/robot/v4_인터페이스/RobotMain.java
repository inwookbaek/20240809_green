package com.lec.robot.v4_인터페이스;

import com.lec.robot.v4_인터페이스.impl.*;
import com.lec.robot.v4_인터페이스.inter.*;

public class RobotMain {

	public static void main(String[] args) {

		System.out.println("=== Robot V4.0 (인터페이스) ===");
		System.out.println();	
		
		CheapRobot cheapRobot = new CheapRobot("CheapRobot", 2);
		System.out.println(cheapRobot.toString());
		cheapRobot.shape();
		cheapRobot.actionWalk();
		cheapRobot.actionRun();
		cheapRobot.setFly(new FlyYes());
		cheapRobot.actionFly();
		cheapRobot.setMissile(new MissileYes());
		cheapRobot.actionMissile();
		cheapRobot.setSword(new SwordWood());
		cheapRobot.actionSword();
		System.out.println("-".repeat(50));
		
		StandardRobot standardRobot = new StandardRobot("StandardRobot", 6);
		System.out.println(standardRobot.toString());
		standardRobot.shape();
		standardRobot.actionWalk();
		standardRobot.actionRun();
		standardRobot.setFly(new FlyYes());
		standardRobot.actionFly();
		standardRobot.setMissile(new MissileICBM());
		standardRobot.actionMissile();
		standardRobot.setSword(new SwordLaser());
		standardRobot.actionSword();
		System.out.println("-".repeat(50));
		
		SuperRobot superRobot = new SuperRobot("SuperRobot", 2);
		System.out.println(superRobot.toString());
		superRobot.shape();
		superRobot.actionWalk();
		superRobot.actionRun();
		superRobot.setFly(new FlyYes());
		superRobot.actionFly();
		superRobot.setMissile(new MissileICBM());
		superRobot.actionMissile();
		superRobot.setSword(new UltraJjangSword());
		superRobot.actionSword();
		System.out.println("-".repeat(50));
	
	}

}

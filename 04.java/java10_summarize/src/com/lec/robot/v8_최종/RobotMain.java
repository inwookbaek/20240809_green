package com.lec.robot.v8_최종;

import com.lec.robot.v8_최종.impl.*;
import com.lec.robot.v8_최종.inter.InterFly;
import com.lec.robot.v8_최종.inter.InterMissile;
import com.lec.robot.v8_최종.inter.InterSword;

public class RobotMain {

	public static void main(String[] args) {
		
		System.out.println("=== Robot V8.0 (최종) ===");
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

	// 실습. V9_실습 : System.out.println("=== Robot V9.0 (실습. 메서드로 초기화) ===");
	// v8에서 
	// 1. Interface속성을 삭제 InterFly InterMissile, InterSword;
	// 2. Robot(String name, int qty)
	// 3. actionFly(InterFly fly) { fly.fly();}
    //    actionMissile(InterMissile missile) { missile.missile(); }
	//    actionSword(InterSword sword) { sword.sword(); }
}

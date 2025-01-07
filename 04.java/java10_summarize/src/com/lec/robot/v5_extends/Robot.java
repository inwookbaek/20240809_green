package com.lec.robot.v5_extends;

import com.lec.robot.v4_인터페이스.inter.InterFly;
import com.lec.robot.v4_인터페이스.inter.InterMissile;
import com.lec.robot.v4_인터페이스.inter.InterSword;

public class Robot {

	private InterFly fly;
	private InterMissile missile;
	private InterSword sword;
	
	public String name;
	public int qty;
	
	public Robot(String name, int qty) {
		this.name = name;
		this.qty = qty;
	}	
	
	// setter
	public void setFly(InterFly fly) { this.fly = fly; }
	public void setMissile(InterMissile missile) { this.missile = missile; }
	public void setSword(InterSword sword) { this.sword = sword; }
	
	// 공통기능
	public void shape() { System.out.println(this.name + "입니다!! 팔, 다리, 머리, 몸통이 있습니다.");}
	public void actionWalk() { System.out.println("걸을 수가 있습니다!!"); }
	public void actionRun() { System.out.println("달릴 수가 있습니다!!"); }
	
	// 개별기능
	public void actionFly() { fly.fly();}
    public void actionMissile() { missile.missile(); }
	public void actionSword() { sword.sword();}
	
	@Override
	public String toString() {
		return String.format("[%s] 현재재고 = %d 개", this.name, this.qty);
	}		
}

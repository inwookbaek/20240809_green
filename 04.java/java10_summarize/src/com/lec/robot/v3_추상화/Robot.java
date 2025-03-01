package com.lec.robot.v3_추상화;

public abstract class Robot {

	public String name;
	public int qty;
	
	public Robot(String name, int qty) {
		this.name = name;
		this.qty = qty;
	}

	public void shape() { System.out.println(this.name + "입니다!! 팔, 다리, 머리, 몸통이 있습니다.");}
	public void actionWalk() { System.out.println("걸을 수가 있습니다!!"); }
	public void actionRun() { System.out.println("달릴 수가 있습니다!!"); }
	
	public abstract void actionFly();
	public abstract void actionMissile();
	public abstract void actionSword();
	
	@Override
	public String toString() {
		return String.format("[%s] 현재재고 = %d 개", this.name, this.qty);
	}	
	
}

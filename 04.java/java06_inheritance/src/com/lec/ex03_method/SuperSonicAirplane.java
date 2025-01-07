package com.lec.ex03_method;

public class SuperSonicAirplane extends Airplane {

	public static final int NORMAL = 1;
	public static final int SUPERSONIC = 2;
	int flymode = NORMAL;
	
	@Override
	void fly() {
		if(flymode == NORMAL) {
			super.fly();  // 부모메서드를 호출가능
		} else {
			System.out.println("초음속비행을 합니다!!");
		}
	}
	
}

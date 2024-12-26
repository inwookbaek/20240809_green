package com.lec.ex03_method;

public class SuperSonicAirplaneMain {

	public static void main(String[] args) {
		
		SuperSonicAirplane ssa = new SuperSonicAirplane();
		
		ssa.takeOff();
		
		ssa.flymode = SuperSonicAirplane.NORMAL;
		ssa.fly();
		
		ssa.flymode = SuperSonicAirplane.SUPERSONIC;
		ssa.fly();
		
		ssa.flymode = SuperSonicAirplane.NORMAL;
		ssa.fly();
		
		ssa.land();
		
	}

}


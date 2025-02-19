package com.lec.robot.v4_인터페이스.impl;

import com.lec.robot.v4_인터페이스.inter.InterSword;

public class SwordNo implements InterSword {

	@Override
	public void sword() {
		System.out.println("공격용 검이 없습니다!!");
	}

}

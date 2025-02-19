package java07_interface.ex03_multi_impl;

import java07_interface.ex02_implements.RemoteControl;

public class SmartTelevisionMain {

	public static void main(String[] args) {

		RemoteControl rc = new SmartTelevisionRemoCon(); // 자동형변환
		rc.powerOn();
		rc.setVolumn(5);
		rc.setMute(true);
		rc.powerOff();
		// 자식객체에서 부모객체로 자동형변환되어 search()메서드 사용불가
		// rc.search("http://www.googl.com");
		System.out.println();
		
		SmartTelevisionRemoCon stvrc = new SmartTelevisionRemoCon();
		stvrc.powerOn();
		stvrc.setVolumn(5);
		stvrc.setMute(false);
		stvrc.search("http://www.googl.com");
		stvrc.powerOff();
		System.out.println();
		
		Seacherable rc1 = stvrc;  // 자동형변환
		// rc1.powerOn();
		// rc1.setVolumn(5);
		// rc1.setMute(false);
		rc1.search("http://www.googl.com");
		// rc1.powerOff();		
		
		RemoteControl.changeBattery();
		
		

	}

}

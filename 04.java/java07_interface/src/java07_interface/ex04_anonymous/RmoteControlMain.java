package java07_interface.ex04_anonymous;

import java07_interface.ex02_implements.RemoteControl;
import java07_interface.ex02_implements.TVRemoCon;

public class RmoteControlMain {

	public static void main(String[] args) {
		
		// 1. 인터페이스는 객체 생성불가
		// RemoteControl rc = new RemoteControl();
		
		// 2. 익명구현객체
		RemoteControl rc;
		int volumn;
		
		// 1) TV RemoCon
		rc = new RemoteControl() {
			
			@Override
			public void setVolumn(int volumn) {
				if(volumn > RemoteControl.MAX_VOLUMN) {
					volumn = RemoteControl.MAX_VOLUMN;
				} else if(volumn < RemoteControl.MIN_VOLUMN) {
					volumn = RemoteControl.MIN_VOLUMN;			
				} else {
					volumn = volumn;
				}
				System.out.println("TV의 현재 볼륨은 " + volumn + "입니다!");
			}
			
			@Override
			public void powerOn() {
				System.out.println("TV를 파워온!!");
			}
			
			@Override
			public void powerOff() {
				System.out.println("TV를 파워오프!!");
			}
		};
		rc.powerOn();
		rc.setMute(false);
		rc.setVolumn(5);
		rc.powerOff();
		System.out.println();		
		
		// 2) Audio RemoCon
		rc = new RemoteControl() {
			
			@Override
			public void setVolumn(int volumn) {
				if(volumn > RemoteControl.MAX_VOLUMN) {
					volumn = RemoteControl.MAX_VOLUMN;
				} else if(volumn < RemoteControl.MIN_VOLUMN) {
					volumn = RemoteControl.MIN_VOLUMN;			
				} else {
					volumn = volumn;
				}
				System.out.println("Audio의 현재 볼륨은 " + volumn + "입니다!");
			}
			
			@Override
			public void powerOn() {
				System.out.println("Audio를 파워온!!");
			}
			
			@Override
			public void powerOff() {
				System.out.println("Audio를 파워오프!!");		
			}
		};
		rc.powerOn();
		rc.setMute(false);
		rc.setVolumn(5);
		rc.powerOff();
		System.out.println();	
		
		// 3. Radio RemoCon
		rc = new RemoteControl() {
			// 라디오기능을 구현
			public void setVolumn(int volumn) {}
			public void powerOn() {}
			public void powerOff() {}
		};
		
		// 4. Wireless Speaker RemoCon
		rc = new RemoteControl() {
			// 무선스피커기능을 구현
			public void setVolumn(int volumn) { /* 무선스피커 기능을 정의 */}
			public void powerOn() {}
			public void powerOff() {}
		};
		
		// 기타등등 RemoCon....
	}

}

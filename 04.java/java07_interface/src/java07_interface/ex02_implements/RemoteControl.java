package java07_interface.ex02_implements;

public interface RemoteControl {

	int MIN_VOLUMN = 0;
	int MAX_VOLUMN = 10;
	// int volumn;
	
	void powerOn();
	void powerOff();
	void setVolumn(int volumn);	
	
	default void setMute(boolean mute) {
		if(mute) {
			System.out.println("음소거!!");
		} else {
			System.out.println("음소거해제!!");			
		}
	}
	
	static void changeBattery() {
		System.out.println("건저지를 교체합니다!");
	}
}

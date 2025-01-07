package java07_interface.ex02_implements;

public class RemoteControlMain {

	public static void main(String[] args) {
		
		// TVRemoCon tvRemoCon = new TVRemoCon();
		// AudioRemoCon audioRemoCon = new AudioRemoCon();
		
		RemoteControl rc;
		rc = new TVRemoCon();  // TV button click
		rc.powerOn();
		rc.setMute(false);
		rc.setVolumn(5);
		rc.powerOff();
		System.out.println();		
			
		rc = new AudioRemoCon();  // Audio button click
		rc.powerOn();
		rc.setMute(false);
		rc.setVolumn(5);
		rc.powerOff();
		System.out.println();
		
		RemoteControl.changeBattery();  // static method
	}

}

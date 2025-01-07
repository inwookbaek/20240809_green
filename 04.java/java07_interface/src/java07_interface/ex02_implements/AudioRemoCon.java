package java07_interface.ex02_implements;

public class AudioRemoCon implements RemoteControl{

	private int volumn;
	
	@Override
	public void powerOn() {
		System.out.println("Audio를 파워온!!");
	}

	@Override
	public void powerOff() {
		System.out.println("Audio를 파워오프!!");	
	}

	@Override
	public void setVolumn(int volumn) {
		if(volumn > RemoteControl.MAX_VOLUMN) {
			this.volumn = RemoteControl.MAX_VOLUMN;
		} else if(volumn < RemoteControl.MIN_VOLUMN) {
			this.volumn = RemoteControl.MIN_VOLUMN;			
		} else {
			this.volumn = volumn;
		}
		System.out.println("Audio의 현재 볼륨은 " + this.volumn + "입니다!");
	}
}

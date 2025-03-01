package java07_interface.ex03_multi_impl;

import java07_interface.ex02_implements.RemoteControl;

public class SmartTelevisionRemoCon implements RemoteControl, Seacherable {

	private int volumn;
	
	@Override
	public void powerOn() {
		System.out.println("스마트TV 파워온!!!");		
	}

	@Override
	public void powerOff() {
		System.out.println("스마트TV 파워오프!!!");		
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
		System.out.println("스마트TV의 현재 볼륨은 " + this.volumn + "입니다!");
	}

	@Override
	public void search(String url) {
		System.out.println(url + "에서 검색기능을 실행합니다!");		
	}

}

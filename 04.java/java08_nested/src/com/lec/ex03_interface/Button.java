package com.lec.ex03_interface;

public class Button {
	
	// 중첩인터페이스
	interface OnClickListner {
		void OnClick();
	}

	OnClickListner listner;
	public void setListner(OnClickListner listner) {
		this.listner = listner;
	}
	
	void click() {
		listner.OnClick();
	}

}

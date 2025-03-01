package com.lec.ex03_interface;

public class YouTube implements Button.OnClickListner{

	@Override
	public void OnClick() {
		System.out.println("유튜브 동영상을 시청합니다!");
	}

}

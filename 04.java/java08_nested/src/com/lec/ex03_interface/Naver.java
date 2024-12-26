package com.lec.ex03_interface;

public class Naver implements Button.OnClickListner {

	@Override
	public void OnClick() {
		System.out.println("네이버로 검색을 합니다!");
	}

}

package com.lec.ex10_abstract._class;

public class SmartPhone extends Phone {

	public SmartPhone(String owner) {
		super(owner);
	}

	public void search(String url) {
		System.out.println(url + "에서 검색을 합니다!");
	}
}

package com.lec.ex03_interface;
public class ButtonMain {

	public static void main(String[] args) {
		
		Button button = new Button();
		
		button.setListner(new YouTube());
		button.click();
		
		button.setListner(new Naver());
		button.click();
		
		button.setListner(new Call());
		button.click();
		
		button.setListner(new Message());
		button.click();
		
		button.setListner(new Google());
		button.click();
		
	}
}

class Call implements Button.OnClickListner {

	@Override
	public void OnClick() {
		System.out.println("전화를 겁니다!!");
	}
	
}

class Message implements Button.OnClickListner {

	@Override
	public void OnClick() {
		System.out.println("문자를 보냅니다!!");
	}
	
}

class Google implements Button.OnClickListner {
	
	@Override
	public void OnClick() {
		System.out.println("googling을 합니다!!");
	}
	
}
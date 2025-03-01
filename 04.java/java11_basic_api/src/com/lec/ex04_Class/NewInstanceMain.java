package com.lec.ex04_Class;
/*
	4. 동적으로 객체를 생성(newinstance())
	
	   Class를 이용하면 new연산자를 사용하지 않아도 객체를 동적으로 생성할 수 있다.
	   이 방법은 코드 작성시에 클래스의 이름을 결정할 수 없고 런타임시에 클래스이름을
	   결정하는 경우에 매우 유용하게 사용된다.
	   
	   Class객체를 얻은 후에 newinstance()메서드를 호출하면 Object타입의 객체를 얻을
	   수가 있다.
*/
public class NewInstanceMain {

	public static void main(String[] args) throws Exception {
		
		// type이 true면 문자를 보내는 객체생성
		// false 문자를 받는 객체를 생성
		boolean type = false;
		
		Class _class = null;
		
		if(type) {
			_class = Class.forName("com.lec.ex04_Class.SendAction");
		} else {
			_class = Class.forName("com.lec.ex04_Class.ReceiveAction");			
		}

		Action action = (Action) _class.newInstance();
		action.execute();
	}

}

interface Action { void execute(); }

class SendAction implements Action {
	@Override 
	public void execute() { System.out.println("문자를 보냅니다!!");}
}

class ReceiveAction implements Action {
	@Override 
	public void execute() { System.out.println("문자를 받습니다!!");}
}













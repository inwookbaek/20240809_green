package com.lec.ex13_static;

/*
	정적메서드와 정적블럭 사용시에 주의할 점
	
	정적메서드 정적블럭을 선언할 때 주의할 점은 객체가 생성되지 않아도
	즉, 객체가 없어도 실행된다는 특징때문에 정적메서드와 정적블럭 내부에
	인스턴트필드나 인스턴트메서드를 사용할 수 없다. 또한, 객체 자기자신의
	참조키워드인 this를 사용할 수 없다.
	
	정적메서드와 정적블럭에서 인스턴트멥버를 사용하고 싶다면 객체를 먼저
	생성하고 참조변수로 접근해야 한다.
	
	또한, main메서드로 정적메서드이기 때문에 동일한 규칙이 적용된다. 이런
	이유로 main에서 객체 생성없이 인스턴스멤버(필드와 메서드)를 바로 사용
	할 수 없다.
*/
public class CarMain {

	int speed;
	void run() {
		System.out.println("현재속도는 " + speed + "km 입니다!");
	}
	
	public static void main(String[] args) {
		
		// speed와 run은 인스턴스멤버이기 때문에 정적멤버인 main에서 접근 불가
		// speed = 120;
		// run();
		
		CarMain cm = new CarMain();
		cm.speed = 120;
		cm.run();
		

	}

	static {
		
	}
}

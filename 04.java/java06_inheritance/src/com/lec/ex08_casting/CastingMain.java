package com.lec.ex08_casting;

/*
	참조타입의 강제타입변환(Casting)
	
	강제타입변환은 부모타입을 자식타입으로 변환하는 것을 말한다. 형변환이 된다고
	해서 모든 부모타입이 자식타입으로 강제형변환할 수 있는 것은 아니다.
	
	강제타입변환이 되는 경우는 자식타입이 부모타입으로 형변환이 된 후에 자식타입
	으로 변환되는 경우에만 강제타입변환을 할 수가 있다.
	
	자식타입이 부모타입으로 자동형변환하면 부모타입에 선언된 필드와 메서드만 사용
	할 수가 있다. 자식타입이 부모타입으로 자동형변환이 되면 부모타입에서 선언된 
	필드와 메서드만 사용할 수 있다. 만약, 자식타입에 선언된 필드와 메서드를 꼭 사용
	해야 하는 경우에는 강제형변환을해서 다시 자식타입으로 변환한 후에 자식타입의
	필드와 메서드를 사용할 수 있다.
*/
public class CastingMain {

	public static void main(String[] args) {
		// 1. 자동형변환(promotion)
		// Child child = new Child();
		// Parent parent = child;
		Parent parent = new Child();   // 자식개체생성과 동시에 형변환(자동) 
		parent.field1 = "사용가능";
		// parent.field2 = "사용불가"; 
		parent.method1();
		parent.method2();     // 자식객체에서 재정의된 메서드호출
		// parent.method3();  // 호출불가
		System.out.println();
		
		// 2. 강제형변환(casting)
		// 직접생성한 부모객체(오리지널)에서 자식객체로 형변환하는 경우 예외발생
		// 자식객체 -> 부모객체(형변환) -> 자식객체로 형변환되는 경우를 강제형변환
		// 이라고 한다. 하지만 부모객체(오리지널) -> (부모타입) 자식객체로 
		// 직접형변화는 것은 불가능하다.
		int val = (int) 3.141592d;
		Parent parent2 = new Parent();
		System.out.println(parent.getClass().getSimpleName() + " : " + parent2.getClass().getSimpleName());
		// 강제형변환은 문법에러는 아니지만 실행중에러 즉, 프로그램이 중단
		Child child = (Child) parent2;  // java.lang.ClassCastException

	}
}
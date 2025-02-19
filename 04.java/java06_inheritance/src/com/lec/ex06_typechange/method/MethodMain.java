package com.lec.ex06_typechange.method;

//	1. 상속 : 부모 vs 자식(메서드추가, 메서드재정의)
//	          method1 vs metho1재정의, method2를 추가
//	2. 형변환	
//	 1) 부모로형변환후 다시 자식으로 형변환하는 경우 		
//		 자식 -> 부모 형변환    -> 자식 형변환
//	      	 	 method1        -> method1
//	      	 	 method2재정의  -> method2재정의
//	      	 	 method3삭제    -> method3복구
//	 2) 부모에서 직접 자식으로 형변환
//	     Casting Exception이 발생	
//	3. method의 매개변수로 전달
//	   methodA(Parent p) <- Child전달
//	 methodB(Child  c) <- 형변환된 Parent는 OK, 오리지널 Parent는 예외발생

public class MethodMain {

	public static void main(String[] args) {

		Child child = new Child();
		child.method1();   // 부모에서 상속받은 메서드
		child.method2();   // 부모에서 상속받은 메서드를 재정의한 메서드
		child.method3();   // 자식에서 추가된 새로운 메서드
		System.out.println();
		
		// 1. 자식객체에서 부모객체로 형변환 : 자동형변환
		Parent parent = child;   // 자동형변환
		// 1) method1? -> 부모객체의 method1이 호출
		parent.method1();
		
		// 2) method2? -> 자식객에서 재정의된 method2가 호출
		parent.method2();
		
		// 3) method3? -> 자식객체에서 새롭게 추가된 method3는
		// 부모타입으로 형변환시에 삭제(짤리기)되기 때문에 호출불가
		// parent.method3(); // 에러
		System.out.println();
		
		// 2. 부모객체와 자식객체가 동일타입인 경우에만 형변환가능
		//    동일타입이 아닐경우는 예외가 발생되기 때문에 두 객체가
		//    동일타입여부를 확인해야 한다.
		
		System.out.println("parent == child :" + (parent == child)); // true, child에서 parent로 형변환
		System.out.println("parent.equals(child) :" + (parent.equals(child))); // true, child에서 parent로 형변환
		System.out.println(parent.getClass().getName()+ " = " + child.getClass().getName());
		System.out.println(parent.getClass().getSimpleName()+ " = " + child.getClass().getSimpleName());
		System.out.println();
		
		Parent parent2 = new Parent();  // 오리지널 부모객체
		System.out.println("parent2 == child :" + (parent2 == child));           // false
		System.out.println("parent2.equals(child) :" + (parent2.equals(child))); // false
		System.out.println(parent2.getClass().getSimpleName()+ " = " + child.getClass().getSimpleName());
		System.out.println("new Parent() == child :" + (new Parent() == child));           // false
		System.out.println();	
		
		// 3. 자식객체 -> 부모객체 -> 자식객체 : 강제형변환
		child = (Child) parent;
		child.method1();   // 부모에서 상속받은 메서드
		child.method2();   // 부모에서 상속받은 메서드를 재정의한 메서드
		child.method3();   // 자식에서 추가된 새로운 메서드		
		System.out.println();
		
		// 4. (오리지널)부모객체 -> 자식객체 : 에러발생
		// java.lang.ClassCastException예외발생
		// child = (Child) parent2; // 에러
	}
}

class Parent{
	void method1() {
		System.out.println("Parent.method1이 호출!!");
	}
	void method2() {
		System.out.println("Parent.method2이 호출!!");		
	}
	
}

class Child extends Parent {

	@Override
	void method2() {
		System.out.println("Child.method2이 호출!!");
	}
	void method3() {
		System.out.println("Child.method3이 호출!!");		
	}
	
}

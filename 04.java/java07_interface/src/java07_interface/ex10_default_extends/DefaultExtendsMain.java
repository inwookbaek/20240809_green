package java07_interface.ex10_default_extends;

/*
	디폴트메서드가 있는 인터페이스의 상속과 구현
	
	인터페이스간에도 상속이 있기 때문에 부모인터페이스에 디폴트메서드가 정의되어 있는 경우
	자식인터페이스에서 디폴트메서드를 사용하는 방법은 3가지가 있다.
	
	1. 디폴트메서드를 단순히 상속만 받는 경우
	2. 디폴트메서드를 재정의 하는 경우
	3. 부모의 디폴트메서드를 자식에서 추상메서드로 재선언하는 경우
*/
public class DefaultExtendsMain {

	public static void main(String[] args) {
		// 1. Parent의 default메서드 상속
		ChildInterface1 ci1 = new ChildInterface1() {
			public void method1() { System.out.println("ChildInterface1.method1(inner type) 호출!!"); }
			public void method3() { System.out.println("ChildInterface1.method3(inner type) 호출!!"); }
		};
		ci1.method1();
		ci1.method2();
		ci1.method3();
		System.out.println();
		
		// 2. Parent의 default메서드를 재정의
		ChildInterface2 ci2 = new ChildInterface2() {
			public void method1() { System.out.println("ChildInterface2.method1(inner type) 호출!!"); }
			public void method3() { System.out.println("ChildInterface2.method3(inner type) 호출!!"); }
		};
		ci2.method1();
		ci2.method2();
		ci2.method3();
		System.out.println();
		
		// 3. Parent의 default메서드를 추상메서드로 재정의
		ChildInterface3 ci3 = new ChildInterface3() {
			public void method1() { System.out.println("ChildInterface3.method1(inner type) 호출!!"); }
			public void method2() { System.out.println("ChildInterface3에서 추상메서드로 재정의후 구현한 method2(inner type) 호출!!"); }
			public void method3() { System.out.println("ChildInterface3.method3(inner type) 호출!!"); }
		};
		ci3.method1();
		ci3.method2();
		ci3.method3();
	}

}





















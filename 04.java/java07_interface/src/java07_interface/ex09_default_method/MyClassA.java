package java07_interface.ex09_default_method;

// method1만 구현한 클래스가 여러개있다고 가정
public class MyClassA implements MyInterface {

	@Override
	public void method1() {
		System.out.println("MyClassA.method1 구현!!");
	}
	
}

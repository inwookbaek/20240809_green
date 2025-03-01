package java07_interface.ex09_default_method;

// 1년뒤에 B클래스에 method2라는 업무가 추가되었다고 가정
public class MyClassB implements MyInterface {

	@Override
	public void method1() {
		System.out.println("MyClassB.method1 구현!!");
	}

	@Override
	public void method3() {
		System.out.println("MyClassB.method3 구현!!");
	}
}

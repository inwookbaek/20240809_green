package java07_interface.ex09_default_method;

public interface MyInterface {
	void method1();
	// void method2();  // 1년뒤에 업무변경으로 추가된 로직(MyClassB만 적용)
	default void method2() {
		System.out.println("1년뒤에 모든 클래스에 적용되는 공통기능이 추가!!!");
	};
	
	// 3년뒤에 새로운 공통기능이 추가
	default void method3() {
		System.out.println("3년뒤에 모든 클래스에 적용되는 공통기능이 추가!!!");		
	}
}

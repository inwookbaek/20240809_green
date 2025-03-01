package java07_interface.ex10_default_extends;

public interface ParentInterface {
	void method1();
	default void method2() { /* 실행블럭 */
		System.out.println("ParentInterface.method2(default) 호출!!!"); }
}

package java07_interface.ex10_default_extends;

public interface ChildInterface3  extends ParentInterface {

	// 3. 부모의 default메서드(method2)를 추상메서드로 재선언
	void method3();
	
	@Override
	void method2();
}

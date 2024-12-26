package java07_interface.ex01_interface;

// interface는 오직 추상메서드(public abstract)만 갖는다.
// 또한, 속성(필드)은 오직 상수(public static final)만 선언할 수 있다.
public interface Animal {

	String KIND = "동물"; // public static final가 생략
	
	// 추상메서드는 실행블럭이 없는 메서드
	void sound(); // public abstract가 생략
	
    // void breath();
	
	// 실행블럭을 갖는 메서드는 인터페이스에서 default메서드로 선언
	default void breath() {
		System.out.println("[공통기능] 동물은 숨을 쉬어야만 한다!!");
	};
	
}

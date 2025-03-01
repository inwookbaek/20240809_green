package java07_interface.ex01_interface;

public class Bird implements Animal {

	@Override
	public void sound() {
		System.out.println("짹짹하고 소리를 낸다!");
	}
	
	@Override
	public void breath() {
		System.out.println("[메서드오버라이딩] 새가 숨을 쉼니다!!");
	}	
}

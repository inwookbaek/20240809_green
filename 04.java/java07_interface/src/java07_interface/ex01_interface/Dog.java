package java07_interface.ex01_interface;

public class Dog implements Animal {

	@Override
	public void sound() {
		System.out.println("멍멍하고 소리를 낸다!");
	}
}

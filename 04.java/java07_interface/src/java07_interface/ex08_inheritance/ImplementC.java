package java07_interface.ex08_inheritance;

public class ImplementC implements InterfaceC {

	@Override
	public void methodA() {
		System.out.println("InterfaceA.methodA 구현!!");
	}

	@Override
	public void methodB() {
		System.out.println("InterfaceB.methodB 구현!!");
	}

	@Override
	public void methodC() {
		System.out.println("InterfaceC.methodC 구현!!");
	}

}

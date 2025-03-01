package java07_interface.ex08_inheritance;

public class InteritanceMain {

	public static void main(String[] args) {
		ImplementC imc = new ImplementC();
		imc.methodA();
		imc.methodB();
		imc.methodC();
		System.out.println();
		
		// 1. 자동형변환
		InterfaceA ia = imc;
		ia.methodA();
		System.out.println();
		
		InterfaceB ib = imc;
		ib.methodB();
		System.out.println();
		
		// 2. 강제형변환
		imc = (ImplementC) ia; 
		imc.methodA();
		imc.methodB();
		imc.methodC();
		System.out.println();
		
		imc = (ImplementC) ib;
		imc.methodA();
		imc.methodB();
		imc.methodC();
		
	}

}

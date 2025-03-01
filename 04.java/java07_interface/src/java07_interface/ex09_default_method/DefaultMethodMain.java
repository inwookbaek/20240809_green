package java07_interface.ex09_default_method;

public class DefaultMethodMain {

	public static void main(String[] args) {
		
		MyInterface mia = new MyClassA();
		mia.method1();
		System.out.println();
		
		// 1년뒤에 새로운 기능인 MyClassB가 추가
		MyInterface mib = new MyClassB();
		mib.method1();
		System.out.print("[MyClassB] ");
		mib.method2();
		
		System.out.print("[MyClassA] ");
		mia.method2();
		System.out.println();
		
		// 3년뒤에 새로운 공통기능 실행
		System.out.print("[MyClassA(원본)] ");
		mia.method3();
		System.out.print("[MyClassB(재정의)] ");
		mib.method3();
		
	}

}

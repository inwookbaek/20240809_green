package com.lec.ex04_Class;
/*
	Class 클래스
	
	1. Class객체얻기(Class.forName(String str), Object.getClass(클래스이름))
	
	   프로그램에서 Class객체를 얻기 위한 방법
	   1) Object클래스의 getClass()메서드와
	   2) Class클래스의 forName()메서드를 이용하는 방법
	   
	   Object는 모든 클래스의 최상위 클래스이므로 모든 클래스에서드에 getClass()메서드를 호출
	   할 수 있다. getClass()메서드는 해당 클래스의 객체가 생성되어 있을 때에만 사용할 수 있
	   지만 객체가 생성되어 있지 않을 경우에 Class객체를 얻을 수가 없다.
	   
	   Class의 생성자는 private으로 선언되어 있기 때문에 new객체생성연산자로 객체를 만들 수가
	   없다. 정적인 메서드 forName()을 이용해서 클래스 정보를 얻을 수 있다. forName()메서드는
	   클래스전체이름 즉, 패키지이름을 포함한 전체 클래스이름을 매개값을 전달 받아서 해당 클
	   래스의 객체를 생성한 후에 그 객체를 리턴한다.
	   
	   Class.forName()메서드는 클래스를 찾지 못했을 경우에 ClassNotFoundException예외를 발생
	   시키기 때문에 예외처리를 해야 한다.
	
	2. Class정보얻기(Reflection)
	
	   getDeclaredConstructors(), getDeclaredFields(), getDeclaredMethods()등의 메서드를 이용
	   해서 해당 클래스이 필드, 생성자, 메서드에 대한 정보를 얻을 수가 있다.
	   이 것을 리플렉션(Reflection)이라고 한다.

*/
public class ClassMain {

	public static void main(String[] args) {
		
		// 객체를 생성하는 방법
		// 1. new객체생성연산자로 생성
		Car car1 = new Car();
		System.out.println(car1.hashCode());
		System.out.println(car1.model);
		System.out.println(car1.getClass());
		System.out.println(car1.getClass().getName());
		System.out.println(car1.getClass().getSimpleName());
		System.out.println();
		
		// 2. Object.getClass()
		Class car2 = car1.getClass();
		System.out.println(car2.hashCode());
		// System.out.println(car2.model);
		System.out.println(car2.getClass());
		System.out.println(car2.getClass().getName());
		System.out.println(car2.getClass().getSimpleName());
		System.out.println(car2.getPackage());
		System.out.println(car2.getPackageName());
		System.out.println();
		
		// 3. Class.forName()
		try {
			Class car3 = Class.forName("com.lec.ex04_Class.Car");
			System.out.println(car3.hashCode());
			// System.out.println(car3.model);
			System.out.println(car3.getClass());
			System.out.println(car3.getClass().getName());
			System.out.println(car3.getClass().getSimpleName());
			System.out.println(car3.getPackage());
			System.out.println(car3.getPackageName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}

class Car {
	String model = "포르쉐 911 카레라";
	int speed;
}


















package com.lec.ex08_Wrapper;

/* 
	포장클래스(Wrapper Class)
	
	java는 기본타입(byte, short, char, int...)은 객체로 생성할 수 없기 때문에 기본타입의
	데이터를 객체로 생성할 수 있도록 지원하는데 이런 객체를 포장객체라고 한다.
	이름에서 알 수 있듯이 기본타입의 값을 내부에 두고 포장하기 때문에 포장객체라고 한다.
	
	포장객체는 포장하고 있는 내부의 기본타입의 값은 외부에서 변경할 수 없다. 만약, 내부
	의 값을 변경하고 싶을 때에는 새로운 포장객체를 만들어야 한다.
	
	포장클래스는 java.lang패키지에 포함되어 있는 각각의 기본타입에 대응하는 클래스가 있다.
	char와 int는 각각 Character과 Integer로 변경되고 나머지 기본타입은 첫 글자가 대문자로
	변경된 이름을 가지고 있다.
	
	기본자료형 대신에 포장클래스를 사용하는 이유는
	1. 객체 또는 클래스가 제공하는 속성(필드, 메서드)를 사용할 수 있다.
	2. 클래스가 제공하는 상수를 사용할 수 있다.(MIN_VALUE or MAX_VALUE등)
	3. 숫자, 문자형으로 형변환 또는 진법전환시에 사용할 수 있다.
	 
	Boxing, Unboxing
	 
	기본타입의 값을 포장객체로 만드는 과정을 boxing이라고 하고 반대로 포장객체에서 기본타입
	의 값을 얻어내는 과정을 unboxing이라고 한다.
	 
	자동박싱과 언박싱?
	 
	기본타입의 값을 직접 박싱, 언박싱하지 않아도 자동으로 박싱과 언박싱이 되는 것을 말한다.
	자동박싱은 포장클래스에 기본값이 대입이 되는 경우를 말하고 자동언박싱은 기본타입에 포장
	객체가 대입되는 경우에 발생한다.
*/
public class WrapperMian1 {

	public static void main(String[] args) {

		// 1. 수동박싱
		// 1) 생성자이용(deprecated)
		@SuppressWarnings("deprecated")
		Integer obj1 = new Integer(100);
		Integer obj2 = new Integer("100");
		System.out.println("int타입의 값의 범위 = " + Integer.MIN_VALUE + " ~ " + obj1.MAX_VALUE);
		System.out.println("obj1.toString() = " + obj1.toString());
		System.out.println("obj1.getClass() " + obj1.getClass());
		System.out.println();
		
		// 2) valueOf()메서드 이용
		Integer obj3 = Integer.valueOf(100);
		Integer obj4 = Integer.valueOf("100");
		
		Double obj5 = Double.valueOf(3.141592);
		System.out.println("double 타입의 값의 범위 = " + Double.MIN_VALUE + " ~ " + obj5.MAX_VALUE);
		System.out.println("obj5.toString() = " + obj5.toString());
		System.out.println("obj5.getClass() " + obj5.getClass());
		System.out.println();
		
		// 2. 자동박싱
		Integer obj6 = 100;
		System.out.println("obj6.intValue() = " + obj6.intValue());
		
		Double obj7 = 3.141592;
		System.out.println("obj7.intValue() = " + obj7.intValue());
		System.out.println("obj7.doubleValue() = " + obj7.doubleValue());
		System.out.println("obj7.toString() = " + obj7.toString());
		System.out.println();
		
		// 3. 언박싱
		int val1 = obj1.intValue();
		int val2 = obj2.intValue();
		int val3 = obj3.intValue();
		int val4 = obj4.intValue();
		
		double val5 = obj5.doubleValue();
		double val6 = obj6.intValue();
		double val7 = obj7.doubleValue();
		
		// 4. 자동언박싱
		// 1) 값대입시
		int val8 = obj6;     // double -> double
		double val9 = obj7;  // double -> double
		double val10 = obj6; // int -> doble
		
		// 2) 연산시
		int result = obj6 + 100;
		System.out.println("result = " + result);
	}

}


















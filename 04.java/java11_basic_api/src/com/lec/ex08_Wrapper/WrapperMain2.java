package com.lec.ex08_Wrapper;

public class WrapperMain2 {

	public static void main(String[] args) {
		
		// 객체(포장)의 비교
		// 포장객체는 내부값을 비교하기 위해 동등연산자(==,!=)를 사용할 수 없다.
		// 동등연산자는 내부값을 비교하는 것이 아니라 참조주소를 비교하기 때문이다.
		
		Integer obj1 = 300;
		Integer obj2 = 300;
		// 포장객체의 hashCode는 재정의되었기 때문에 메모리주소가 아니라 값을 출력
		System.out.println("obj1.hashCode, obj2.hashCode = " + obj1.hashCode() + ", " + obj2.hashCode());
		System.out.println();
		
		System.out.println("포장객체의 비교 결과(동등연산자) = " + (obj1 == obj2)); // false
		
		// 내부값을 비교하려면 언박싱한 값을 비교해야 한다.
		System.out.println("포장객체의 비교 결과(언박싱) = " + (obj1.intValue() == obj2.intValue())); // true
		System.out.println("포장객체의 비교 결과(equals) = " + (obj1.equals(obj2))); // true
		
	}

}

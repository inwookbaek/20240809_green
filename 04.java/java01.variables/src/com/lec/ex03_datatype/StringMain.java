package com.lec.ex03_datatype;

public class StringMain {

	public static void main(String[] args) {
		// 참조타입 - class, array, interface, enum
		// 문자열타입인 String은 class이기 때문에 참조타입(class)이다.
		// 즉 String(문자열)은 Reference타입이다. 문자열을 String이라는 class이다.
		// Java에서 class로 선언된 모든 것은 참조타입이다.
		// Java에서는 문자열을 표현할 때는 큰 따옴표로 선언하다.
		// 작은 따옴표는 char타입을 선언할 때 사용한다.
		
		char c1 = 'A';
		// char c2 = "A";  // 에러
		// char c3 = 'AA'; // 에러, char은 한 개의 문자(내부적으로는 정수)만 저장 가능
		
		String s1 = "A";
		String s2 = "AAAAAAAAAAAAA";
		System.out.println("s2의 클래스이름 =  " + s2.getClass().getName());
		
		// 참조타입은 객체가 저장되어 있는 주소를 참조한다는 의미
		System.out.println("s1의 값 = " + s1 + ", s2의 값 = " + s2);
		
		// 1. hashCode() - 객체가 저장되어 있는 메모리주소를 int로 리턴하는 메서드
		// 문자열 A or AAAAA...가 저장되어 있는 주소를 리턴
		System.out.println("s1이 참조하는 메모리주소 = " + s1.hashCode() 
			+ ", s2가 참조하는 메모리주소 = " + s2.hashCode());
		
		// 참조타입은 3개의 멤버가 있는데 필드(속성), 생성자, 메서드
		// 속성=length, 여러개의 String(), 여러개의 메서드 replace()....
		
		// 2. toString() : String으로 생성된 객체는 저장되어 있는 값을 리턴
		System.out.println("s1.toString() = " + s1.toString());
		System.out.println("s2.toString() = " + s2.toString());
		
		// 3. literal vs constant
		// 공통점은 값을 변경할 수 없다.
		// 다른점은 다른 객체에서 공용으로 사용가능여부
		// Java에서 상수의 변수명을 관례로 모두 대문자로 선언하고
		// 단어마다 언더바(_)로 연결해서 명명한다.
		// Java에서 상수는 final로 선언된 변수를 의미하고 상수는
		// 초기값을 정의해야 하고 변경할 수 없다.
		final int min_value = 0; // 에러는 아니나 관례에 어긋난다.
		final int MIN_VALUE = 0; // 관례에 따른 명명방법
		
		// 상수 즉, final로 선언된 변수를 값을 변경할 수 없다.
		// min_value = 100; 에러
		// MIN_VALUE = 100; 에러
		
		final int MAX_VALUE; // 선언만 되고 초기화하지 않된 경우
		MAX_VALUE = 1000;    // 값을 변경하는 것이 아니라 초기값을 대입한 경우
		// MAX_VALUE = 1000;    // 초기화가 되었을 경우 값을 변경할 경우 에러
		
		final double PI = 3.141592;
	}

}

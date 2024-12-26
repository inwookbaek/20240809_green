package com.lec.ex02_variables;
/*
	1. 변수란?
	
	   프로그램은 작업을 처리하는 과정에서 필요에 따라 데이터를 메모리에 저장한다. 이때 변수를
	   사용하는데 변수(variable)는 값을 저장하는 하나의 메모리 공간을 의미한다.
	   변수란 이름은 프로그램에서 수시로 값이 변동 될 수 있기 때문이다. 따라서 변수란 변동된
	   값을 가진 하나의 메모리 공간이라고 한다. 변수에는 복수개의 값을 저장할 수 있고 하나의 값
	   만 저장할 수 있다. 즉, 변수란 하나 또는 복수의 값을 저장할 수 있는 메모리공간을 의미한다.
	
	2. 변수의 명명규칙
	
	   1) 첫 번째 글자는 문자이거나 $, 언더바(_)로 시작해야 하고 숫자로 시작할 수 없다.(필수)
	   2) 변수명은 대문자와 소문자가 구분이 된다.(필수)
	   3) 첫 번째 글자는 소문자로 시작하고 단어 구분마다 대문자로 선언(관례)
	      - camel case(firstName), snake case(first_name)
	   4) 변수명의 길이에는 제한이 없다.
	   5) 한글변수명도 가능하다. 하지만 사용하지는 않는다(관례)
	   6) 자바예약어(if, for...)는 사용할 수 없다.   
	
	3. 변수의 사용
	   
	   변수를 사용한다는 것은 변수에 값을 저장하고 읽는 행위를 말한다. 변수에 값을 저장할 때는
	   대입연산자(=)를 사용한다. 일반 수학에서는 "=는 좌변과 우변이 같다"의미이지만 프로그램에서
	   는 우변의 변수 or 값을 좌변의 변수에 대입(저장)한다는 의미이다.
	   
	   변수를 선언하고 처음의 값을 저장할 경우는 초기화한다고 하고 이 값을 초기값이라고 한다.
	   따라서, 자바에서는 변수에 초기값을 준다는 의미는 "변수의 초기화"라고 한다.
	   
	   리터럴(literal), 상수(constant, 변하지 않는 값)는 같은 의미이지만 Java에서는 literal은
	   값을 변경할 수 없는 자료형, 상수는 "한 번값을 저장하면 변경할 수 없는 변수"로 정의하기 때
	   문에 Java에서는 literal과 constant는 구분해서 사용한다.
*/
public class VariableMain1 {

	public static void main(String[] args) {
		// 1. 변수의 선언방법
		// 1) 데이터타입 변수명;            -- 변수선언만, 선언만 된 변수는 사용할 수 없다.
		// 2) 데이터타입 변수명 = 초기값;   -- 변수선언과 동시에 초기화
		// 3) 한번 선언된 데이터타입은 원칙적으로 변경할 수 없다.
		// 4) 데이터타입 : 
		//    - 기본타입 : 숫자(byte, short, char, int, double, float), 문자(String), boolean
		//    - 참조타입 : 클래스, 배열, enum, 인터페이스
		
		int firstNum;  // 변수선언만
		// System.out.println(firstNum); // 초기화가 되지 않은 변수는 사용불가 즉, 에러
		
		firstNum = 10;  // 변수초기화
		System.out.println("첫 번째 선언한 변수(firstNum)의 값 = " + firstNum);
		
		// 아래의 선언은 전부 에러
		// firstNum = "문자열"; 에러 - 한번 선언된 변수의 자료는 해당 데이터타입의 자료만 대입할 수 있다.
		// int firstNum = 20;   에러 - 한번선언된 변수의 재선언은 할 수 없다.
		// firstNum = 10.0;     에러 - 정수(int)가 아닌 다른 자료형의 데이터는 대입불가
		
		// 대소문자구분
		int firstnum = 20;
		System.out.println("두 번째 선언한 변수(firstnum)의 값 = " + firstnum);
		
		int FirstNum = 30; // 변수명선언의 관례에 벗어난 선언
		System.out.println("세 번째 선언한 변수(FirstNum)의 값 = " + FirstNum);
		
		int 한글명변수 = 40;
		System.out.println("네 번째 선언한 변수(한글명변수)의 값 = " + 한글명변수);
		System.out.println();
		
		// 2. 변수선언
		int val1;   // 선언만하고 초기화 하지 않은 경우
		// System.out.println("val1 = " + val1); 에러 : 초기화되지 않은 변수 사용 불가
		
		val1 = 100;
		System.out.println("val1 = " + val1);
		
		int val2 = 200; // 선언과 동시에 초기화
		System.out.println("val2 = " + val2);
		
		int val3;
		// int val4 = val3 + 30; 에러 : 사용불가
		
	}

}














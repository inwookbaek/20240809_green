package com.lec.ex15_final;
/*
	final필드와 static final(상수)필드
	
	1. final 필드
	
	   final이라는 의미는 최종적인 의미를 가지고 있다. 이 의미는 final필드는 초기값이
	   지정되면 더 이상 수정할 수 없다는 것이다.
	   
	   final필드의 초기값을 지정하는 방법은 2가지 방법밖에 없는데
	   1) 필드선언시에 초기값을 부여하는 방법
	   2) 생성자를 통해서 객체를 생성할 때 final필드에 초기값을 부여하는 방법
	   
	   단순값이라면 필드선언시에 부여하는 것이 제일 간단하지만 복잡한 초기화코드가
	   필요하거나 객체 생성시에 외부 데이터로 초기화해야할 경우 생성자에서 객체가
	   생성될 때 초기값을 부여한다.
	   
	   생성자는 final필드를 초기화해야 하는데 만약 초기화 되지 않은 final필드가 
	   있을 경우에 컴파일(문법)에러가 발생된다.
	
	
	2. static final 필드
	
	   일반적으로 불변의 값을 상수라고 한다. 불변값은 수학에서 자주 사용하는 원주율의
	   PI값 or 지구의 둘레, 무게등이 해당된다.
	   
	   이런 불변의 값을 저장하는 필드를 자바에서 상수(constant)라고 한다. final필드는
	   한번 초기화가 되면 더이상 변경할 수가 필드이지만 상수라고 하지 않는다.
	   왜냐하면 불변의 값은 객체마다 저장할 필요가 없는 공통으로 사용되는 값으로 여러
	   가지 값으로 초기화 될 수 없기 때문이다.
	   
	   final필드는 객체마다 저장되고 생성자의 매개값을 통해서 여러가지 값을 객체별로
	   가질 수 있기 때문에 상수가 될 수 없다.
	   
	   그래서 Java에서의 상수는 final이면서 static이어야 한다. static은 정적멤버이기
	   때문에 객체마다 저장되지 않고 클래스에만 포함된다. 그리고 한번 초기화가 되면
	   그 이후에는 수정할 수가 없다.
*/
public class Korean {

	final double pi;         
	final double pi1 =3.141592; // 1번째 방법
	static final double PI = 3.141592;
	
	static final String NATION = "대한민국";
	String name;
	final String ssn;
	
	public Korean(double pi) {
		this.pi = pi;        // 2번째 방법 
		this.ssn = "";
	}
	
	public Korean(String name, String ssn) {
		this.pi = 3.141592;
		this.name = name;
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "Korean [name=" + name + ", ssn=" + ssn + "]";
	}
	
}

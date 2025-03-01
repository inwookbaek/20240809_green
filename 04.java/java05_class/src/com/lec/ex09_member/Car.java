package com.lec.ex09_member;
/*
클래스의 멤버

1. 필드(field, 속성, 클래스변수)

   객체의 고유속성을 저장하는 곳이다. 선언방법은 변수와 유사하지만 필드를 변수라고 하지 않는다.
   변수는 생성자와 메서드내에서만 사용되고 생성자와 메서드의 실행이 종료가 되면 자동으로 소멸
   되지만 필드는 생성자와 메서드뿐만 아니라 전역적으로 사용(전역변수개념)되고 객체가 소멸되지
   않는 이상 객체와 함께 메모리에 존재한다. 즉, 객체가 소멸되기 전까지 메모리에 존재하고 객체가
   소멸될 때 같이 소멸된다.
   
2. 메서드(method, 기능, 동작의 역할)

   메서드는 객체의 동작(기능, 행위)에 해당하는 실행 블럭이다. 이 블럭은 이름을 부여하고 이 이름
   으로 메서드를 호출하게 되면 블럭안에 정의된 실행문들이 일괄적으로 실행되고 그 결과를 호출한 
   곳으로 리턴하는 역할을 한다.

3. 생성자(Constructor, 객체의 초기화)

   생성자는 new 객체생성연산자로 호출되는 특별한 블럭이다. 생성자의 역할은 객체를 생성할 때 객체의
   초기화를 담당한다. 즉, 필드를 초기화 하거나 메서드를 호출해서 객체를 생성할 수 있도록 한다.
   
   기본생성자(default constructor)
   
   모든 클래스는 생성자 반드시 존재해야 하며 한 개이상의 생성자를 가질 수 있다. 단, 복수의 생성자를
   정의할 경우에는 매개변수의 갯수, 데이터타입 및 순서가 달라야 한다. 즉, 동일한 형태의 시그니처를
   갖는(중복되는) 생성자는 정의할 수가 없다.
   
   기본 생성자는 별도로 정의하지 않아도 자바 컴파일러(javac.exe)가 컴파일시에 자동으로 추가한다.
   하지만, 개발자가 기본생성자가 아닌 생성자를 정의할 경우에는 자동으로 추가되지 않는다. 따라서
   기본생성자이외의 다른 생성자를 정의하고 기본생성자가 필요할 경우에는 반드시 정의를 해야 기본
   생성자를 사용할 수 있다.
   
   클래스가 public접근제한자로 선언이 되어 있다면 기본생성자도 자동으로 public으로 선언되지만 클래스가
   public선언되어 있지 않았을 경우에 기본생성자도 public으로 선언되지 않는다.

*/
public class Car {
	
	// 1. 필드(속성)
	String company = "벤츠";
	String model;
	String color;
	int maxSpeed = 300;
	int speed = 0;
	
	// 2. 생성자(객체생성 및 초기화)
	public Car() {
		System.out.println("기본생성자가 호출되었습니다!!");
	}
	
	public Car(String model) {
		System.out.println("문자열(" + model + ")생성자가 호출되었습니다!!");
	}
	
	public Car(int maxSpeed) {
		System.out.println("maxSpeed(" + maxSpeed + ")생성자가 호출되었습니다!!");
	}
	
	public Car(int maxSpeed, int speed) {
		System.out.println("maxSpeed, speed(" + maxSpeed + "," + speed + ")생성자가 호출되었습니다!!");
	}
	
	public Car(String color, int maxSpeed) {
		this.color = color;
		this.maxSpeed = maxSpeed;
		System.out.println("Color, maxSpeed(" + color + ", "+ maxSpeed + ")생성자가 호출되었습니다!!");		
	}

	public Car(String model, String color, int maxSpeed, int speed) {
		// super() // 부모생성자 즉, Object()가 생략된 상태, 따라서 부모생성자에 기본생성자가 반드시 존재해야 한다.
		this.model = model;
		this.color = color;
		this.maxSpeed = maxSpeed;
		this.speed = speed;
		System.out.println(super.toString());  // 메모리주소가 16진수로 출력
		System.out.println(this.toString());   // 재정의가 않되었을 경우 부모의 toString()이 호출되고 재정의시 자기 toString()이 호출
	}

	public Car(int i, String string, String string2, int j) {
		this.maxSpeed = i;
		this.model = string;
		this.color = string2;
		this.speed = i;
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "Car [company=" + company + ", model=" + model + ", color=" + color + ", maxSpeed=" + maxSpeed
				+ ", speed=" + speed + "]";
	}

	// 3. 메서드(기능)
	void powerOn() {}
	void run() {}
	void stop() {}
	void powerOff() {}
	
	
}
























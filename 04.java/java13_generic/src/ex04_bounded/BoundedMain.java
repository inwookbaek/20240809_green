package ex04_bounded;
/*
	제한된 파라미터(<T extends 상위타입>, <? super 하위타입>)
	
	타입파라미터에 지정되는 구체적인 데이터타입을 제한할 필요가 있을 경우, 예를 들어
	숫자를 연산하는 제네릭메서드가 있다면 이 메서드는 매개값을 숫자타입만 전달되어야 한다.
	즉, 매개값을 Number or 하위클래스(Byte, Integer, Double...)등의 참조타입(객체)만 전달
	되어야 한다. 이것이 제한된 타입파라미터(Bounded Type Parameter)가 필요한 이유이다.
	
	제한된 파라미터를 선언하려면 타입파라미터뒤에 extends키워드를 선언하고 상위타입을 명시
	하면 된다. 상위타입은 클래스뿐만 아니라 인터페이스도 가능한데 인터페이스라고 해서 implements
	로 선언하지 않고 클래스 동일하게 extends키워드를 사용한다.
	
	타입파라미터에 지정되는 구체적인 타입은 상위타입이거나 상위타입을 상속 또는 구현한 
	하위클래스, 하위인터페이스만 전달이 가능하다.

*/
public class BoundedMain {

	public static void main(String[] args) {
		
		int result = Util.compare(10, 20);  // int 타입
		System.out.println("10과 20의 비교결과 = " + result);
		
		result = Util.compare(10.0, 20.0);  // double 타입
		System.out.println("10.0과 20.0의 비교결과 = " + result);
		
		// result = Util.compare(10, "10");
		// Number와 하위타입(Iteger, Double...)만 대입하도록 제한됨
		
		// 1st step : int -> Integer, double -> Double
		// 2nd step : t1->Integer -> Double, t2 -> None 
		result = Util.compare(10, 10d);
		System.out.println("10과 10d의 비교결과 = " + result);		
	}
}

class Util {
	
	public static <T extends Number> int compare(T t1, T t2) {
		Double x = t1.doubleValue();
		Double y = t2.doubleValue();
		return Double.compare(x, y);
		// return ((x<y) ? -1 : ((x==y) ? 0 : 1));
	}
}























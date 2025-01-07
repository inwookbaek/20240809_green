package ex03_method;

/*
	제네릭메서드 : <T> 리턴타입<T> 메서드명(T, t) {}
	
	제네릭메서드는 매개타입과 리턴타입으로 타입파라미터를 갖는 메서드를 말한다. 제네릭메서드를
	선언하는 방법은 리턴타입앞에 <>를 추가하고 타입파라미터를 기술한 후에 리턴타입과 매개타입
	으로 타입파라미터를 사용하면 된다.
	
	public <T> 리턴타입<T> 메서드명(T, t) { return 리턴타입 }
	
	제네릭메서드는 2가지 방식으로 호출할 수 있다. 코딩할 때 타입파라미터의 구체적인 타입을 명시
	적으로 지정해도 되고, 컴파일러가 매개값의 타입으로 보고 구체적인 타입을 추저하도록 할 수 
	있다.
*/
public class MethodMain {

	public static void main(String[] args) {
		// 1. 일반메서드
		Box g_box = Util.method(new Robot());
//		g_box = Util.method(new Apple());
//		g_box = Util.method(new Car());
//		g_box = Util.method(new String("망치"));
		
		Box<Robot> 장난감로봇박스 = Util.method(new Robot());
//		Box<Apple> 사과박스 = Util.method(new Apple());
//		Box<Car> 자동차박스 = Util.method(new Car());
//		Box<String> 공구박스 = Util.method(new String("망치"));
//		Box<Integer> 숫자박스 = Util.method(Integer.valueOf(100)); 

		// 2. 제네릭메서드
		Box<Robot> 로봇박스 = Util.boxing(new Robot());
		Box<Apple> 과일박스 = Util.boxing(new Apple());
		Box<Car> 차고 = Util.boxing(new Car());
		Box<String> 문자상자 = Util.boxing(new String("망치"));
		Box<Integer> 숫자상자 = Util.boxing(Integer.valueOf(100));
		
	}

}

class Util {
	// 1. 일반메서드
	public static Box method(Robot robot) {
		Box box = new Box();
		box.set(robot);
		return box;
	}
//
//	public static Box method(String string) {
//		Box box = new Box();
//		box.set(string);
//		return box;
//	}
//
//	public static Box method(Car car) {
//		Box box = new Box();
//		box.set(car);
//		return box;
//	}
//
//	public static Box method(Apple apple) {
//		Box box = new Box();
//		box.set(apple);
//		return box;
//	}
	
	
	// 2. 제네릭메서드
	public static <T> Box<T> boxing(T t) {
		Box<T> box = new Box<>();
		box.set(t);
		return box;
	}
}


class Box<T> {
	private T t;
	public T get() { return t; }
	public void set(T t) { this.t = t; }
}

class Robot {
	@Override
	public String toString() {
		return "나는 로봇입니다!";
	}
}

class Apple {
	@Override
	public String toString() {
		return "나는 사과입니다!";
	}
}

class Car {
	@Override
	public String toString() {
		return "나는 자동차입니다!";
	}
}



















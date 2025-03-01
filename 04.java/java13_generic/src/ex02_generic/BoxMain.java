package ex02_generic;

public class BoxMain {

	public static void main(String[] args) {
		
		Box<Apple> 과일상자 = new Box();
		과일상자.set(new Apple());
		// 과일상자.set(new Hammer()); (x)
		// 제네릭은 컴파일단계에서부터 에러를 미연에 방지할 수 있다.
		// 객체저장시에 사전에 정의된 특정 객체만 주입이되도록 제한하는 방법이다.
		// 과일상자.set(new String("아담"));
		// 과일상자.set(new int[] {1,2,34,5});
		Apple apple = 과일상자.get();
		System.out.print(apple);
		System.out.println(" -> 과일상자 = " + apple.getClass());
		
		Box<Hammer> 공구상자 = new Box<>();
		// 공구상자.set(new Apple());
		공구상자.set(new Hammer());
		// 공구상자.set(new String("아담"));
		// 공구상자.set(new int[] {1,2,34,5});
		Hammer hammer = 공구상자.get();
		System.out.print(hammer);
		System.out.println(" -> 공구상자 = " + hammer.getClass());	
		
		Box<String> 문자상자1 = new Box();
		Box<String> 문자상자2 = new Box<>();       // 통상적인 방법
		Box<String> 문자상자3 = new Box<String>();
		Box 문자상자4 = new Box<String>(); // 우변에만 있을 경우는 non-generic
		
		문자상자1.set("아담");
		문자상자1.set(new String("아담"));
		String adam = 문자상자1.get();
		System.out.print(adam);
		System.out.println(" -> 문자상자1 = " + adam.getClass());			
	}

}

class Box<T> {
	private T t;
	public T get() { return this.t; }
	public void set(T t) { this.t = t; }
	
}

class Apple {
	@Override
	public String toString() {
		return "사과";
	}
}

class Hammer {
	@Override
	public String toString() {
		return "망치";
	}
}
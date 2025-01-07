package ex01_nongeneric;

public class BoxMain {

	public static void main(String[] args) {
		
		Box 과일상자 = new Box();
		
		과일상자.setObject(new Apple());            // 자동형변환 Apple -> Object
		Apple apple = (Apple) 과일상자.getObject(); // 강제형변환 Object -> Apple
		System.out.print(apple);
		System.out.println(" -> 과일상자 = " + apple.getClass());
		
		과일상자.setObject(new Hammer());
		Hammer hammer = (Hammer) 과일상자.getObject();
		System.out.print(hammer);
		System.out.println(" -> 과일상자 = " + hammer.getClass());
		System.out.println();
		
		Box 공구상자 = new Box();
		공구상자.setObject(new Hammer());
		hammer = (Hammer) 공구상자.getObject();
		System.out.print(hammer);
		System.out.println(" -> 공구상자 = " + hammer.getClass());	
		
		공구상자.setObject(new Apple());
		apple = (Apple) 공구상자.getObject();
		System.out.print(apple);
		System.out.println(" -> 공구상자 = " + apple.getClass());
		
		공구상자.setObject(new String("아담"));
		String adam = (String) 공구상자.getObject();
		System.out.print(adam);
		System.out.println(" -> 공구상자 = " + adam.getClass());		
	
		공구상자.setObject(Integer.valueOf(100));
		공구상자.setObject(Double.valueOf(3.14));
		공구상자.setObject('A');
		공구상자.setObject(new int[] {1,2,3,4,5});
		공구상자.setObject(new Banna());
		공구상자.setObject(new Driver());

	}

}

class Box {
	private Object object;
	public Object getObject() { return this.object; }
	public void setObject(Object object) { this.object = object; }
}

class Apple {
	@Override
	public String toString() {
		return "사과";
	}
}
class Banna {}
class Hammer {
	@Override
	public String toString() {
		return "망치";
	}
}
class Driver {}
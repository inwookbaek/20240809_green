package java07_interface.ex01_interface;

public class AnimalMain {

	public static void main(String[] args) {
		
		// interface는 추상클래스처럼 객체생성불가
		// Animal animal = new Animal();
		
		// 1. interface 
		Animal dog = new Dog();
		Animal cat = new Cat();
		Animal bird = new Bird();
		dog.sound();
		dog.breath();
		cat.sound();
		cat.breath();
		bird.sound();
		bird.breath();
		System.out.println();
		
		// 2. 타입변환(자동형변환)
		animalSound(new Dog());
		animalSound(new Cat());
		animalSound(new Bird());
		animalSound(new Tiger());
		animalSound(new Lion());
	}

	private static void animalSound(Animal animal) {
		animal.breath();
		animal.sound();
	}
}
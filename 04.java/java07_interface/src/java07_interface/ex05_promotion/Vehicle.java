package java07_interface.ex05_promotion;

public interface Vehicle {
	void run();
}

class Taxi implements Vehicle {

	@Override
	public void run() {
		System.out.println("택시를 운전합니다!!");
	}
}

class Bus implements Vehicle {

	@Override
	public void run() {
		System.out.println("버스를 운전합니다!!");
	}
}

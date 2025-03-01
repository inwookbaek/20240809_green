package java07_interface.ex07_instanceof;

public class Taxi implements Vehicle {

	@Override
	public void run() {
		System.out.println("택시를 운전합니다!!");
	}
	
	public void checkNight() {
		System.out.println("야간할증요금을 확인합니다!");
	}
}

package java07_interface.ex06_casting;

public class DriverMain {

	public static void main(String[] args) {
		
		// 1. 자동형변환
		Vehicle vehicle = new Bus();   
		vehicle.run();
		// vehicle.checkFare(); (x)
		System.out.println();
		
		// 2. 강제형변환
		Bus bus = (Bus) vehicle;
		bus.run();
		bus.checkFare();
	}

}

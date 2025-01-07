package java07_interface.ex07_instanceof;

public class Driver {

	void drive(Vehicle vehicle) {
		vehicle.run();
		System.out.println(vehicle.getClass());
		// Bus bus = (Bus) vehicle;
		// bus.checkFare();

		// Taxi taxi = (Taxi) vehicle;
		// taxi.checkNight();
	
		if(vehicle instanceof Bus) {
			Bus bus = (Bus) vehicle;
			bus.checkFare();			
		} else if(vehicle instanceof Taxi) {
			Taxi taxi = (Taxi) vehicle;
			taxi.checkNight();			
		} else if(vehicle instanceof 자전거) {
			자전거 bicycle = new 자전거();
			bicycle.checkRoad();
		}
		System.out.println();		
	}
}





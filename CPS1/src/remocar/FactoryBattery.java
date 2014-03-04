package remocar;

public class FactoryBattery {

	public static Battery newBattery (int n, int x) {
		Battery battery = new Battery(x);
		
		for (int i = 1 ; i<n ; i++) {
			battery.bindPuissanceService(new Battery(x));
		}
		return battery;
	}
	
	
}

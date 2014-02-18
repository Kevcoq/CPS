package bridge.components;

import bridge.services.BridgeService;
import bridge.services.CarSensorClientService;
import bridge.services.SensorData;

public class Bridge implements 
	/* provide */
	CarSensorClientService,
	BridgeService {
	
	private int nbInCars;
	private int nbOutCars;
	private int limit;
		
	public Bridge() {
		// do nothing
	}
	


	@Override
	public int getLimit() {
		return limit;
	}

	@Override
	public boolean isFull() {
		return (nbInCars + nbOutCars) == limit;
	}

	@Override
	public int getNbIn() {
		return nbInCars;
	}

	@Override
	public int getNbOut() {
		return nbOutCars;
	}

	@Override
	public int getNbCars() {
		return nbInCars + nbOutCars;
	}


	@Override
	public void init(int lim) {
		limit = lim;
	}

	@Override
	public void init() {
		init(200); // petit "bug" lié à l'inclusion des init par héritage en Java
	}



	@Override
	public void enterIn() {
		nbInCars++;
	}

	@Override
	public void leaveIn() {
		nbInCars--;
	}

	@Override
	public void enterOut() {
		nbOutCars++;
	}

	@Override
	public void leaveOut() {
		nbOutCars--;
	}

	@Override
	public void enter() {
		if(nbInCars>nbOutCars) {
			nbOutCars++;
		} else {
			nbInCars++;
		}
	}

	@Override
	public void leave() {
		if(nbInCars>nbOutCars) {
			nbInCars--;
		} else {
			nbOutCars--;
		}
	}


	@Override
	public void senseCar(SensorData data) {
		if(data.getName().equals("InIsland")) {
			System.out.println("Bridge : New car enters from island");
			enterIn();
		} else if(data.getName().equals("OutIsland")) {
			System.out.println("Bridge : Car leaves to island");
			leaveIn();
		} else if(data.getName().equals("InMainland")) {
			System.out.println("Bridge : New car enters from mainland");
			enterOut();
		} else if(data.getName().equals("OutMainland")) {
			System.out.println("Bridge : Car leaves to mainland");
			leaveOut();
		}
		
		System.out.println("Bridge : nbCars = "+getNbCars());
	}


}

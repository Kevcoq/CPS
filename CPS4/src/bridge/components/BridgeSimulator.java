package bridge.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import bridge.services.BridgeControllerService;
import bridge.services.CarSensorServerService;
import bridge.services.RequireBridgeControllerService;
import bridge.services.RequireCarSensorServerService;

public class BridgeSimulator implements 
	/* require */
	RequireCarSensorServerService,
	RequireBridgeControllerService {

	private Map<String,CarSensorServerService> sensors;
	private BridgeControllerService controller;
	
	private Random rand;
	
	public BridgeSimulator() {
		sensors = new HashMap<String, CarSensorServerService>();
		controller = null;
		rand = null;
	}
	
	public void init(long seed) {
		rand = new Random(seed);
	}


	@Override
	public void bindCarSensorServerService(CarSensorServerService service) {
		sensors.put(service.getName(),service);
	}	

	@Override
	public void bindBridgeControllerService(BridgeControllerService service) {
		controller = service;
	}
	
	public void validateComponent() {
		if(controller==null) {
			throw new Error("Missing bridge controller component");
		}
		if(!sensors.containsKey("InIsland")) {
			throw new Error("Missing in island sensor");
		}
		if(!sensors.containsKey("OutIsland")) {
			throw new Error("Missing out island sensor");
		}
		if(!sensors.containsKey("InMainland")) {
			throw new Error("Missing in mainland sensor");
		}
		if(!sensors.containsKey("OutMainland")) {
			throw new Error("Missing out mainland sensor");
		}
	}

	
	public void stepRandom() {
		if(rand.nextBoolean()) {
			sensors.get("InIsland").activate();
		}

		if(rand.nextBoolean()) {
			sensors.get("InMainland").activate();
		}
		if(rand.nextBoolean()) {
			sensors.get("OutIsland").activate();
		}
		if(rand.nextBoolean()) {
			sensors.get("OutMainland").activate();
		}
	}

	public void simulateRandom(int nbSteps) {
		for(int i=0;i<nbSteps;i++) {
			stepRandom();
			controller.control();
		}
	}


}

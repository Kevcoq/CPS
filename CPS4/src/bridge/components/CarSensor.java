package bridge.components;

import bridge.services.CarSensorClientService;
import bridge.services.CarSensorServerService;
import bridge.services.RequireCarSensorClientService;
import bridge.services.SensorData;

public class CarSensor implements
	/* require */
	RequireCarSensorClientService,
	/* provide */
	CarSensorServerService {
	private String name;
	private CarSensorClientService client;
	
	public CarSensor(String name) {
		this.name = name;
		client = null;
	}
	
	public void init() {
		// rien à initialiser
	}	

	@Override
	public void bindCarSensorClientService(CarSensorClientService service) {
		client = service;
	}
	
	public void validateComponent() {
		if(client==null) {
			throw new Error("Missing client component");
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void activate() {
		System.out.println("Sensor activated : "+ name);
		client.senseCar(new SensorData(name));
	}
	
	
}

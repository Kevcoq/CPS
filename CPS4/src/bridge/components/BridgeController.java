package bridge.components;

import bridge.services.BridgeControllerService;
import bridge.services.BridgeService;
import bridge.services.RequireBridgeService;
import bridge.services.RequireTrafficLightService;
import bridge.services.TrafficLightService;

public class BridgeController implements 
	/* require */
	RequireTrafficLightService,
	RequireBridgeService,
	/* provide */
	BridgeControllerService {

	
	private TrafficLightService islandLight;
	private TrafficLightService mainlandLight;

	private BridgeService bridge;
	
	
	public BridgeController() {
		islandLight = null;
		mainlandLight = null;
		bridge = null;
	}
	
	public void init() {
		// rien à initialiser
	}

	@Override
	public void bindBridgeService(BridgeService service) {
		bridge = service;
	}


	@Override
	public void bindTrafficLightService(String name, TrafficLightService service) {
		if(name.equals("ISLAND")) {
			islandLight = service;
		} else {
			mainlandLight = service;
		}
	}
	
	public void validateComponent() {
		if(bridge==null) {
			throw new Error("Missing bridge component");
		}
		if(islandLight==null) {
			throw new Error("Missing island traffic light component");
		}
		if(mainlandLight==null) {
			throw new Error("Missing mainland traffic light component");
		}
	}

	
	public void control() {
		if(!bridge.isFull() && !islandLight.isRed()) {
			islandLight.changeRed();
			mainlandLight.changeGreen();
		} else if(islandLight.isRed()){
			islandLight.changeGreen();
			mainlandLight.changeRed();
		}
	}

	@Override
	public boolean canEnter() {
		return !bridge.isFull();
	}

	@Override
	public boolean canLeave() {
		return bridge.getNbCars() > 0;
	}
	

	
	
}

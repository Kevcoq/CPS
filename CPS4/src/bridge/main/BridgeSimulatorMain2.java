package bridge.main;

import bridge.components.Bridge;
import bridge.components.BridgeController;
import bridge.components.BridgeSimulator;
import bridge.components.CarSensor;
import bridge.components.TrafficLight;
import bridge.contracts.BridgeComponentContract;
import bridge.contracts.TrafficLightContract;

public class BridgeSimulatorMain2 {
	public static void main(String...args) {
		// composants
		Bridge bridge = new BridgeComponentContract();
		BridgeController controller = new BridgeController();
		BridgeSimulator simulator = new BridgeSimulator();
		TrafficLightContract inIslandLight = new TrafficLightContract(new TrafficLight("From island"));
		TrafficLightContract inMainlandLight = new TrafficLightContract(new TrafficLight("From mainland"));
		CarSensor inIslandSensor = new CarSensor("InIsland");
		CarSensor outIslandSensor = new CarSensor("OutIsland");
		CarSensor inMainlandSensor = new CarSensor("InMainland");
		CarSensor outMainlandSensor = new CarSensor("OutMainland");
		
		// initialisations
		bridge.init(10); // 10 voitures max.
		controller.init();
		simulator.init(424242); // il faut pouvoir rejouer
		inIslandLight.init();
		inMainlandLight.init();
		inIslandSensor.init();
		outIslandSensor.init();
		inMainlandSensor.init();
		outMainlandSensor.init();
		
		// liaisons
		simulator.bindBridgeControllerService(controller);
		simulator.bindCarSensorServerService(inIslandSensor);
		simulator.bindCarSensorServerService(outIslandSensor);
		simulator.bindCarSensorServerService(inMainlandSensor);
		simulator.bindCarSensorServerService(outMainlandSensor);
		controller.bindBridgeService(bridge);
		controller.bindTrafficLightService("ISLAND", inIslandLight);
		controller.bindTrafficLightService("MAINLAND", inMainlandLight);
		inIslandSensor.bindCarSensorClientService(bridge);
		outIslandSensor.bindCarSensorClientService(bridge);
		inMainlandSensor.bindCarSensorClientService(bridge);
		outMainlandSensor.bindCarSensorClientService(bridge);
		
		// validations
		simulator.validateComponent();
		controller.validateComponent();
		inIslandSensor.validateComponent();
		outIslandSensor.validateComponent();
		inMainlandSensor.validateComponent();
		outMainlandSensor.validateComponent();
		
		// modifs
		inIslandLight.switchOn();
		inMainlandLight.switchOn();
				
		// simulation
		simulator.simulateRandom(100);
		
	}
}

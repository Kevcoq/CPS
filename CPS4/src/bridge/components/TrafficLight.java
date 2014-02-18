package bridge.components;

import bridge.services.TrafficLightService;

public class TrafficLight implements
	/* provide */
	TrafficLightService {
	
	private final String name;
	private boolean on;
	private boolean green;
	private boolean red;
	
	public TrafficLight(String name) {
		this.name = name;
	}
	
	@Override
	public void init() {
		on = false;
		green = false;
		red = true;
	}

	
	@Override
	public boolean isOn() {
		return on;
	}

	@Override
	public void switchOn() {
		on = true;
		red = false;
		green = true;
	}

	@Override
	public void switchOff() {
		on = false;
		red = true;
		green = false;
	}

	@Override
	public boolean isGreen() {
		return green;
	}

	@Override
	public boolean isRed() {
		return red;
	}

	private String getColorName() {
		if(isGreen()) {
			return "GREEN";
		}
		if(isRed()) {
			return "RED";
		}
		return "UNKNOWN";
	}
	
	
	@Override
	public void changeGreen() {
		System.out.println("Traffic light '"+name+"': change to GREEN (was: "+getColorName()+")");
		green = true;
		red = false;
	}

	@Override
	public void changeRed() {
		System.out.println("Traffic light '"+name+"': change to RED (was: "+getColorName()+")");
		green = false;
		red = true;
	}
	

}

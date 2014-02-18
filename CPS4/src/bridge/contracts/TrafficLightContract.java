package bridge.contracts;

import bridge.services.TrafficLightService;

public class TrafficLightContract extends LightContract implements TrafficLightService {

	public TrafficLightContract(TrafficLightService delegate) {
		super(delegate);
	}
	
	@Override
	protected TrafficLightService getDelegate() {
		return (TrafficLightService) super.getDelegate();
	}
	
	@Override
	public boolean isGreen() {
		// pre
		if(!(isOn())) {
			Contractor.defaultContractor().preconditionError("TrafficLightService","isGreen","The traffic light is not switched on");
		}
		return getDelegate().isGreen();
	}
	
	@Override
	public boolean isRed() {
		// pre
		if(!(isOn())) {
			Contractor.defaultContractor().preconditionError("TrafficLightService","isRed","The traffic light is not switched on");
		}
		return getDelegate().isRed();
	}

	public void checkInvariant() {
		// puis vérifier au-dessus
		super.checkInvariant();
		// inv'
		boolean inv = isOn() && ((isGreen()==true && isRed()==false) || (isGreen()==false && isRed()==true));
		if(!inv) {
			Contractor.defaultContractor().invariantError("TrafficLightService","The traffic light has two distinct colors !");
		}
	}
	
	@Override
	public void init() {
		// run
		getDelegate().init();   // do not inherit from init, so do not call super !
		// inv post
		checkInvariant();
		// post
		if(!(isOn()==false)) {
			Contractor.defaultContractor().postconditionError("TrafficLightService","init","The light should be off after initialization");
		}
	}

	
	@Override
	public void switchOn() {
		// pre' (no added pre)
		// run
		super.switchOn(); // refinement : the contract will be tested (invariant pre & post included)
		// post'
		if(!(isGreen())) {
			Contractor.defaultContractor().postconditionError("TrafficLightService","switchOn","The light has not turned green");
		}
		if(!(!isRed())) {
			Contractor.defaultContractor().postconditionError("TrafficLightService","switchOn","The light is still red");
		}
	}

	@Override
	public void changeGreen() {
		// pre
		if(!(isOn())) {
			Contractor.defaultContractor().preconditionError("TrafficLightService","changeGreen","The light should be on");
		}
		if(!(isGreen()==false && isRed()==true)) {
			Contractor.defaultContractor().preconditionError("TrafficLightService","changeGreen","The light is not red");
		}
		// inv pre
		checkInvariant();
		// run
		getDelegate().changeGreen();
		// inv post
		checkInvariant();
		// post
		if(!(isOn())) {
			Contractor.defaultContractor().postconditionError("TrafficLightService","changeGreen","The light should be still on");
		}
		if(!(isGreen() == true)) {
			Contractor.defaultContractor().postconditionError("TrafficLightService","changeGreen","The light has not turned green");
		}		
		if(!(isRed() == false)) {
			Contractor.defaultContractor().postconditionError("TrafficLightService","changeGreen","The light is still red");
		}		
	}

	@Override
	public void changeRed() {
		// pre
		if(!(isOn())) {
			Contractor.defaultContractor().preconditionError("TrafficLightService","changeRed","The light should be on");
		}
		if(!(isGreen()==true && isRed()==false)) {
			Contractor.defaultContractor().preconditionError("TrafficLightService","changeRed","The light is not green");
		}
		// inv pre
		checkInvariant();
		// run
		getDelegate().changeRed();
		// inv post
		checkInvariant();
		// post
		if(!(isOn())) {
			Contractor.defaultContractor().postconditionError("TrafficLightService","changeRed","The light should be still on");
		}
		if(!(isRed() == true)) {
			Contractor.defaultContractor().postconditionError("TrafficLightService","changeRed","The light has not turned redŒ");
		}		
		if(!(isGreen() == false)) {
			Contractor.defaultContractor().postconditionError("TrafficLightService","changeRed","The light is still green");
		}		
	}

}

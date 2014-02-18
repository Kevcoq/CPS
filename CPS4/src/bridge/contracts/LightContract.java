package bridge.contracts;

import bridge.decorators.LightDecorator;
import bridge.services.LightService;

public class LightContract extends LightDecorator {

	public LightContract(LightService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		// rien à vérifier
	}
	
	@Override
	public void init() {
		// run
		super.init();
		// inv post
		checkInvariant();
		// post
		if(!(isOn()==false)) {
			Contractor.defaultContractor().postconditionError("LightService","init","The light should be off after initialization");
		}
	}
	
	@Override
	public void switchOn() {
		// pre
		if(!(!isOn())) {
			Contractor.defaultContractor().preconditionError("LightService","switchOn","The light is already on");
		}
		// captures
		boolean isOn_atPre = isOn();
		// inv pre
		checkInvariant();
		// run
		super.switchOn();
		// inv post
		checkInvariant();
		// post
		if(!(isOn() == !isOn_atPre)) {
			Contractor.defaultContractor().postconditionError("LightService","switchOn","The light has not switched on");
		}
	}

	@Override
	public void switchOff() {
		// pre
		if(!(isOn())) {
			Contractor.defaultContractor().preconditionError("LightService","switchOff","The light is not on");
		}
		// captures
		boolean isOn_atPre = isOn();
		// inv pre
		checkInvariant();
		// run
		super.switchOff();
		// inv post
		checkInvariant();
		// post
		if(!(isOn() == !isOn_atPre)) {
			Contractor.defaultContractor().postconditionError("LightService","switchOff","The light has not switched off");
		}
	}
}

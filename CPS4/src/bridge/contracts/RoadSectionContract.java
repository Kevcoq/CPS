package bridge.contracts;

import bridge.decorators.RoadSectionDecorator;
import bridge.services.RoadSectionService;

public class RoadSectionContract extends RoadSectionDecorator {

	public RoadSectionContract(RoadSectionService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		// inv: getNbCars() >= 0
		if(!(getNbCars() >= 0)) {
			Contractor.defaultContractor().invariantError("RoadSectionService","The number of cars should be positive");
		}
	}
	
	@Override
	public void enter() {
		// captures
		int getNbCars_atPre = getNbCars();
		// inv pre
		checkInvariant();
		// run
		super.enter();
		// int post
		checkInvariant();
		// post: getNbCars() == getNbCars()@pre + 1 
		if(!(getNbCars() == getNbCars_atPre + 1)) {
			Contractor.defaultContractor().preconditionError("RoadSectionService", "enter", "The cars count did not increase");
		}
	}
	
	@Override
	public void leave() {
		// pre: getNbCars() > 0
		if(!(getNbCars() > 0)) {
			Contractor.defaultContractor().preconditionError("RoadSectionService", "leave", "The number of cars is not strictly positive");
		}
		// captures
		int getNbCars_atPre = getNbCars();
		// inv pre
		checkInvariant();
		// run
		super.leave();
		// int post
		checkInvariant();
		// post: getNbCars() == getNbCars()@pre - 1 
		if(!(getNbCars() == getNbCars_atPre - 1)) {
			Contractor.defaultContractor().postconditionError("RoadSectionService", "leave", "The cars count did not decrease");
		}
	}
	
}

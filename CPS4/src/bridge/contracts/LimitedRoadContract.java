package bridge.contracts;

import bridge.decorators.LimitedRoadDecorator;
import bridge.services.LimitedRoadService;

public class LimitedRoadContract extends LimitedRoadDecorator {

	public LimitedRoadContract(LimitedRoadService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		// remarque : include et non refine donc on n'hérite
		// pas des invariants de RoadSectionService, il faut refaire des tests.

		/* A COMPLETER */

		// inv: isFull() == (getNbCars() == getLimit())
		// inv: getNbCars() <= getLimit()
		if (isFull() && (getNbCars() != getLimit())) {
			Contractor.defaultContractor().invariantError(
					"LimitedRoadContract",
					"isFull() && (nbCars != getLimite())");
		}
		if (getNbCars() > getLimit()) {
			Contractor.defaultContractor().invariantError(
					"LimitedRoadContract", "nbCars > getLimit");
		}
	}

	@Override
	public void init(int lim) {
		checkInvariant();
		/*
		 * pre: lim>0 post: getLimit() == lim
		 */
		if (lim <= 0)
			Contractor.defaultContractor().preconditionError(
					"LimitedRoadContract", "init", "lim < 0");
		getDelegate().init(lim);
		checkInvariant();
		if (getLimit() != lim)
			Contractor.defaultContractor().postconditionError(
					"LimitedRoadContract", "init", "GetLim != lim");
	}

}

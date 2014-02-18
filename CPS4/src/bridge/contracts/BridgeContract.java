package bridge.contracts;

import bridge.services.BridgeService;

public class BridgeContract extends LimitedRoadContract implements
		BridgeService {

	public BridgeContract(BridgeService delegate) {
		super(delegate);
	}

	@Override
	protected BridgeService getDelegate() {
		return (BridgeService) super.getDelegate();
	}

	@Override
	public int getNbIn() {
		return getDelegate().getNbIn();
	}

	@Override
	public int getNbOut() {
		return getDelegate().getNbOut();
	}

	public void checkInvariant() {
		// TODO
		// raffinement donc
		super.checkInvariant();
		if (getNbCars() != (getNbIn() + getNbOut())) {
			Contractor.defaultContractor().invariantError("BridgeContract",
					"nbcars != in + out");
		}
	}

	@Override
	public void init() {
		// TODO
		checkInvariant();
		getDelegate().init();
		checkInvariant();
	}

	@Override
	public void init(int lim) {
		// TODO
		checkInvariant();
		getDelegate().init(lim);
		checkInvariant();
	}

	@Override
	public void enterIn() {
		// TODO
		int nbin = getNbIn();
		checkInvariant();
		getDelegate().enterIn();
		checkInvariant();
		// post
		if (getNbIn() != nbin + 1) {
			Contractor.defaultContractor().postconditionError("BridgeContract",
					"enterIn", "nbin != nbin +1");
		}
	}

	@Override
	public void leaveIn() {
		// TODO
		int nbin = getNbIn();
		int nbout = getNbOut();
		// pre
		if (nbin <= 0) {
			Contractor.defaultContractor().preconditionError("BridgeContract",
					"leaveIn", "Nb < 0");
		}
		checkInvariant();
		getDelegate().leaveIn();
		checkInvariant();
		// post
		if (getNbIn() != nbin - 1)
			Contractor.defaultContractor().postconditionError("BridgeContract",
					"leaveIn", "nbin != nbin-1");
		if (getNbOut() != nbout)
			Contractor.defaultContractor().postconditionError("BridgeContract",
					"leaveIn", "nbout != nbout");
	}

	@Override
	public void enterOut() {
		// TODO
		int nbout = getNbOut();
		checkInvariant();
		getDelegate().enterOut();
		checkInvariant();
		if (getNbOut() != nbout + 1) {
			Contractor.defaultContractor().postconditionError("BridgeContract",
					"enterOut", "nbout != nbout+1");
		}
	}

	@Override
	public void leaveOut() {
		// TODO
		int nbin = getNbIn();
		int nbout = getNbOut();
		// pre
		if (nbout <= 0) {
			Contractor.defaultContractor().preconditionError("BridgeContract",
					"leaveOut", "Nbout < 0");
		}
		checkInvariant();
		getDelegate().leaveOut();
		checkInvariant();
		// post
		if (getNbOut() != nbout - 1)
			Contractor.defaultContractor().postconditionError("BridgeContract",
					"leaveOut", "nbout != nbout -1");
		if (getNbIn() != nbin)
			Contractor.defaultContractor().postconditionError("BridgeContract",
					"leaveOut", "nbin != nbin");
	}

}

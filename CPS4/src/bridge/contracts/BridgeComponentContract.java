package bridge.contracts;

import bridge.components.Bridge;
import bridge.services.BridgeService;
import bridge.services.CarSensorClientService;

public class BridgeComponentContract extends Bridge
	implements BridgeService , CarSensorClientService {

	private final BridgeContract bridgeContract;
	private boolean operatorCall;
	private boolean getNbCarsCall;
	private boolean getLimitCall;
	private boolean isFullCall;
	private boolean getNbInCall;
	private boolean getNbOutCall;
	
	public BridgeComponentContract() {
		bridgeContract = new BridgeContract(this);
		operatorCall = false;
		getNbCarsCall = false;
		getLimitCall = false;
		isFullCall = false;
		getNbInCall = false;
		getNbOutCall = false;
	}
	
	public void checkInvariant() {
		if(!operatorCall) {
			operatorCall = true;
			bridgeContract.checkInvariant();
			operatorCall = false;
		} else {
			// rien à faire
		}
	}
	
	@Override
	public int getNbCars() {
		if(!getNbCarsCall) {
			getNbCarsCall = true;
			int val = bridgeContract.getNbCars();
			getNbCarsCall = false;
			return val;
		} else {
			return super.getNbCars();
		}
	}
	
	@Override
	public int getLimit() {
		if(!getLimitCall) {
			getLimitCall = true;
			int val = bridgeContract.getLimit();
			getLimitCall = false;
			return val;
		} else {
			return super.getLimit();
		}
	}
	
	@Override
	public boolean isFull() {
		if(!isFullCall) {
			isFullCall = true;
			boolean val = bridgeContract.isFull();
			isFullCall = false;
			return val;
		} else {
			return super.isFull();
		}		
	}
	
	@Override
	public int getNbIn() {
		if(!getNbInCall) {
			getNbInCall = true;
			int val = bridgeContract.getNbIn();
			getNbInCall = false;
			return val;
		} else {
			return super.getNbIn();
		}
	}

	@Override
	public int getNbOut() {
		if(!getNbOutCall) {
			getNbOutCall = true;
			int val = bridgeContract.getNbOut();
			getNbOutCall = false;
			return val;
		} else {
			return super.getNbOut();
		}
	}
	
	@Override
	public void init() {
		if(!operatorCall) {
			operatorCall = true;
			bridgeContract.init();
			operatorCall = false;
		} else {
			super.init();
		}
	}

	@Override
	public void init(int lim) {
		if(!operatorCall) {
			operatorCall = true;
			bridgeContract.init(lim);
			operatorCall = false;
		} else {
			super.init(lim);
		}
	}
	@Override
	public void enterIn() {
		if(!operatorCall) {
			operatorCall = true;
			bridgeContract.enterIn();
			operatorCall = false;
		} else {
			super.enterIn();
		}
	}

	@Override
	public void leaveIn() {
		if(!operatorCall) {
			operatorCall = true;
			bridgeContract.leaveIn();
			operatorCall = false;
		} else {
			super.leaveIn();
		}
	}

	@Override
	public void enterOut() {
		if(!operatorCall) {
			operatorCall = true;
			bridgeContract.enterOut();
			operatorCall = false;
		} else {
			super.enterOut();
		}
	}

	@Override
	public void leaveOut() {
		if(!operatorCall) {
			operatorCall = true;
			bridgeContract.leaveOut();
			operatorCall = false;
		} else {
			super.leaveOut();
		}
	}
	

	
}

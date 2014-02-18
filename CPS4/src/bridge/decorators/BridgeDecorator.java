package bridge.decorators;

import bridge.services.BridgeService;

public class BridgeDecorator extends LimitedRoadDecorator implements BridgeService {
	
	public BridgeDecorator(BridgeService delegate) {
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

	@Override
	public void init() {
		getDelegate().init();
	}

	@Override
	public void init(int lim) {
		getDelegate().init(lim);
	}

	@Override
	public void enterIn() {
		getDelegate().enterIn();
	}

	@Override
	public void leaveIn() {
		getDelegate().leaveIn();
	}

	@Override
	public void enterOut() {
		getDelegate().enterOut();
	}

	@Override
	public void leaveOut() {
		getDelegate().leaveOut();
	}

}

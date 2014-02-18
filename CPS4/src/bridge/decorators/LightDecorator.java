package bridge.decorators;

import bridge.services.LightService;

public class LightDecorator implements LightService {
	private final LightService delegate;
	
	public LightDecorator(LightService delegate) {
		this.delegate = delegate;
	}
	
	protected LightService getDelegate() {
		return delegate;
	}
	
	@Override
	public boolean isOn() {
		return getDelegate().isOn();
	}

	@Override
	public void init() {
		getDelegate().init();
	}

	@Override
	public void switchOn() {
		getDelegate().switchOn();
	}

	@Override
	public void switchOff() {
		getDelegate().switchOff();
	}

}

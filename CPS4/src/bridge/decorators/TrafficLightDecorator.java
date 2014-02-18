package bridge.decorators;

import bridge.services.TrafficLightService;

public class TrafficLightDecorator extends LightDecorator implements TrafficLightService {
	
	public TrafficLightDecorator(TrafficLightService delegate) {
		super(delegate);
	}
	
	@Override
	protected TrafficLightService getDelegate() {
		return (TrafficLightService) super.getDelegate();
	}

	@Override
	public boolean isGreen() {
		return getDelegate().isGreen();
	}

	@Override
	public boolean isRed() {
		return getDelegate().isRed();
	}

	@Override
	public void changeGreen() {
		getDelegate().changeGreen();
	}

	@Override
	public void changeRed() {
		getDelegate().changeRed();
	}
	

}

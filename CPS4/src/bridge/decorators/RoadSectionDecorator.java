package bridge.decorators;

import bridge.services.RoadSectionService;

public class RoadSectionDecorator implements RoadSectionService {
	private final RoadSectionService delegate;
	
	public RoadSectionDecorator(RoadSectionService delegate) {
		this.delegate = delegate;
	}
	
	protected RoadSectionService getDelegate() {
		return delegate;
	}
	
	@Override
	public int getNbCars() {
		return delegate.getNbCars();
	}

	@Override
	public void init() {
		delegate.init();
	}

	@Override
	public void enter() {
		delegate.enter();
	}

	@Override
	public void leave() {
		delegate.leave();
	}

}

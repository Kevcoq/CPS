package bridge.decorators;

import bridge.services.LimitedRoadService;

public class LimitedRoadDecorator extends RoadSectionDecorator implements LimitedRoadService {
	
	public LimitedRoadDecorator(LimitedRoadService delegate) {
		super(delegate);
	}
	
	@Override
	protected LimitedRoadService getDelegate() {
		return (LimitedRoadService) super.getDelegate();
	}
	
	@Override
	public int getLimit() {
		return getDelegate().getLimit();
	}

	@Override
	public boolean isFull() {
		return getDelegate().isFull();
	}

	@Override
	public void init() {
		getDelegate().init();
	}


	@Override
	public void init(int lim) {
		getDelegate().init(lim);
	}

}

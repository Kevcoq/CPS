package lift.decorators;

import lift.services.CommandsService;
import lift.services.DoorStatus;
import lift.services.LiftService;
import lift.services.LiftStatus;

public class LiftDecorator implements LiftService {
	private final LiftService delegate;

	public LiftDecorator(LiftService delegate) {
		this.delegate = delegate;
	}

	@Override
	public Integer getMinLevel() {
		return delegate.getMinLevel();
	}

	@Override
	public Integer getMaxLevel() {
		return delegate.getMaxLevel();
	}

	@Override
	public Integer getLevel() {
		return delegate.getLevel();
	}

	@Override
	public DoorStatus getDoorStatus() {
		return delegate.getDoorStatus();
	}

	@Override
	public LiftStatus getLiftStatus() {
		return delegate.getLiftStatus();
	}

	@Override
	public CommandsService getCommands() {
		return delegate.getCommands();
	}

	@Override
	public void init(Integer minLevel, Integer maxLevel) {
		delegate.init(minLevel, maxLevel);
		getCommands().init();
	}

	@Override
	public void beginMoveUp() {
		delegate.beginMoveUp();
	}

	@Override
	public void stepMoveUp() {
		delegate.stepMoveUp();
	}

	@Override
	public void endMoveUp() {
		delegate.endMoveUp();
	}

	@Override
	public void beginMoveDown() {
		delegate.beginMoveDown();
	}

	@Override
	public void stepMoveDown() {
		delegate.stepMoveDown();
	}

	@Override
	public void endMoveDown() {
		delegate.endMoveDown();
	}

	@Override
	public void openDoor() {
		delegate.openDoor();
	}

	@Override
	public void closeDoor() {
		delegate.closeDoor();
	}

	@Override
	public void doorAck() {
		delegate.doorAck();
	}

	@Override
	public void selectLevel(int level) {
		delegate.selectLevel(level);
	}

}

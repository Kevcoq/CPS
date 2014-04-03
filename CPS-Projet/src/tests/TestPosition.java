package tests;

import implem.Position;
import contracts.PositionContract;
import tests.abstrait.AbstractTestPosition;

public class TestPosition extends AbstractTestPosition {

	@Override
	public void beforeTests() {
		setPosition(new PositionContract(new Position()));
	}

}

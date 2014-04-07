package tests;

import implem.Position;
import tests.abstrait.AbstractTestPosition;
import contracts.PositionContract;

public class TestPosition extends AbstractTestPosition {

	@Override
	public void beforeTests() {
		setPosition(new PositionContract(new Position()));
	}

}

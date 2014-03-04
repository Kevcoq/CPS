package lift.test;

import lift.contracts.LiftContract;
import liftimpl3.Lift3;

public class TestImpl3 extends AbstractLiftTest {

	@Override
	public void beforeTests() {
		setLift(new LiftContract(new Lift3()));
	}

}

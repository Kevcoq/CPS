package tests;

import implem.Chose;
import tests.abstrait.AbstractTestChose;
import contracts.ChoseContract;

public class TestChose extends AbstractTestChose {

	@Override
	public void beforeTests() {
		setChose(new ChoseContract(new Chose()));
	}

}

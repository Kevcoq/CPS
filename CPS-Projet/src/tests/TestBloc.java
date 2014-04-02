package tests;

import implem.Bloc;
import contracts.BlocContract;
import tests.abstrait.AbstractTestBloc;

public class TestBloc extends AbstractTestBloc {

	@Override
	public void beforeTests() {
		setBloc(new BlocContract(new Bloc()));
	}

}

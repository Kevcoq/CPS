package tests;

import implem.Bloc;
import tests.abstrait.AbstractTestBloc;
import contracts.BlocContract;

public class TestBloc extends AbstractTestBloc {

	@Override
	public void beforeTests() {
		setBloc(new BlocContract(new Bloc()));
	}

}

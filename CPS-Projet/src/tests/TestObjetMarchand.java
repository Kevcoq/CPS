package tests;

import implem.ObjetMarchand;
import tests.abstrait.AbstractTestObjetMarchand;
import contracts.ObjetMarchandContract;

public class TestObjetMarchand extends AbstractTestObjetMarchand {

	@Override
	public void beforeTests() {
		setObjet(new ObjetMarchandContract(new ObjetMarchand()));
	}

}

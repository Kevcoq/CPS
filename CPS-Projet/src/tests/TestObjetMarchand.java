package tests;

import implem.ObjetMarchand;
import contracts.ObjetMarchandContract;
import tests.abstrait.AbstractTestObjetMarchand;

public class TestObjetMarchand extends AbstractTestObjetMarchand {

	@Override
	public void beforeTests() {
		setObjet(new ObjetMarchandContract(new ObjetMarchand()));
	}

}

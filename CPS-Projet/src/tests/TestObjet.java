package tests;

import implem.Objet;
import tests.abstrait.AbstractTestObjet;
import contracts.ObjetContract;

public class TestObjet extends AbstractTestObjet {

	@Override
	public void beforeTests() {
		setObjet(new ObjetContract(new Objet()));
	}

}

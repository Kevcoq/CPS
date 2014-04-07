package tests;

import implem.GestionCombat;
import tests.abstrait.AbstractTestGestionCombat;
import contracts.GestionCombatContract;

public class TestGestionCombat extends AbstractTestGestionCombat {

	@Override
	public void beforeTests() {
		setGestionCombat(new GestionCombatContract(new GestionCombat()));
	}

}

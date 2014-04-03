package tests;

import implem.GestionCombat;
import contracts.GestionCombatContract;
import tests.abstrait.AbstractTestGestionCombat;

public class TestGestionCombat extends AbstractTestGestionCombat {

	@Override
	public void beforeTests() {
		setGestionCombat(new GestionCombatContract(new GestionCombat()));
	}

}

package tests;

import implem.BugGestionCombat;
import tests.abstrait.AbstractTestGestionCombat;
import contracts.GestionCombatContract;

public class TestBUGGestionCombat extends AbstractTestGestionCombat {

	@Override
	public void beforeTests() {
		setGestionCombat(new GestionCombatContract(new BugGestionCombat()));
	}

}

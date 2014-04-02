package tests;

import implem.Gangster;
import tests.abstrait.AbstractTestPersonnage;
import contracts.PersonnageContrat;

public class TestGangster extends AbstractTestPersonnage {

	@Override
	public void beforeTests() {
		setPerso(new PersonnageContrat(new Gangster()));
	}

}

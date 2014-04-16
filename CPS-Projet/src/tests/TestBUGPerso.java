package tests;

import implem.BugPersonnage;
import tests.abstrait.AbstractTestPersonnage;
import contracts.PersonnageContrat;

public class TestBUGPerso extends AbstractTestPersonnage {

	@Override
	public void beforeTests() {
		setPerso(new PersonnageContrat(new BugPersonnage()));
	}

}

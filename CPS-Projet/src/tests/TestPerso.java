package tests;

import implem.Personnage;
import tests.abstrait.AbstractTestPersonnage;
import contracts.PersonnageContrat;

public class TestPerso extends AbstractTestPersonnage {

	@Override
	public void beforeTests() {
		setPerso(new PersonnageContrat(new Personnage()));
	}

}

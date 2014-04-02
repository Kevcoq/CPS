package tests;

import implem.Personnage;
import contracts.PersonnageContrat;
import tests.abstrait.AbstractTestPersonnage;

public class TestPerso extends AbstractTestPersonnage {

	@Override
	public void beforeTests() {
		setPerso(new PersonnageContrat(new Personnage()));
	}

}

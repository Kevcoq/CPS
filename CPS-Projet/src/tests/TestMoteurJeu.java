package tests;

import implem.MoteurJeu;
import tests.abstrait.AbstractTestMoteurJeu;
import contracts.MoteurJeuContract;

public class TestMoteurJeu extends AbstractTestMoteurJeu {

	@Override
	public void beforeTests() {
		setMoteurJeu(new MoteurJeuContract(new MoteurJeu()));
	}

}

package tests;

import implem.MoteurJeu;
import contracts.MoteurJeuContract;
import tests.abstrait.AbstractTestMoteurJeu;

public class TestMoteurJeu extends AbstractTestMoteurJeu {

	@Override
	public void beforeTests() {
		setMoteurJeu(new MoteurJeuContract(new MoteurJeu()));
	}

}

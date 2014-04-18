package tests;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

/**
 * Test globale pour pouvoir faire un jar executable
 * 
 * @author Kevin & Quentin
 * 
 */
public class _GlobalTest {
	public static void main(String[] args) {
		JUnitCore runner = new JUnitCore();
		runner.addListener(new TextListener(System.out));
		runner.run(TestBloc.class);
		runner.run(TestChose.class);
		runner.run(TestObjet.class);
		runner.run(TestObjetMarchand.class);
		runner.run(TestObjetEquipable.class);
		runner.run(TestPosition.class);
		runner.run(TestTerrain.class);
		runner.run(TestPerso.class);
		runner.run(TestGangster.class);
		runner.run(TestGestionCombat.class);
		runner.run(TestMoteurJeu.class);
	}
}

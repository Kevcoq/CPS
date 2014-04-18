package tests;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

/**
 * Test des bug de personnage pour pouvoir faire un jar executable
 * 
 * @author Kevin & Quentin
 * 
 */
public class _BugPersoTest {
	public static void main(String[] args) {
		JUnitCore runner = new JUnitCore();
		runner.addListener(new TextListener(System.out));
		runner.run(TestBUGPerso.class);
	}
}

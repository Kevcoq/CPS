package tests.abstrait;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contracts.base.PreconditionError;
import enumeration.COMMANDE;
import services.MoteurJeuService;

public abstract class AbstractTestMoteurJeu {
	private MoteurJeuService moteur;

	protected AbstractTestMoteurJeu() {
		moteur = null;
	}

	protected final MoteurJeuService getChose() {
		return moteur;
	}

	protected final void setMoteurJeu(MoteurJeuService moteur) {
		this.moteur = moteur;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		moteur = null;
	}

	// ///////////////////////////////////////////////////////
	// /////////////// PRE //////////////////
	//
	//
	//
	// Init
	@Test
	public void testInit() {
		moteur.init(20, 4, 10, 100);
		Assert.assertTrue(true);
	}

	@Test
	public void testInitFail1() {
		try {
			moteur.init(-20, 4, 10, 100);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail2() {
		try {
			moteur.init(20, -4, 10, 100);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail3() {
		try {
			moteur.init(20, 4, -10, 100);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail4() {
		try {
			moteur.init(20, 4, 10, -100);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail5() {
		try {
			moteur.init(-20, 4, 10, 0);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	//
	// pasJeu
	@Test
	public void testPasJeu1() {
		moteur.init(20, 4, 10, 100);
		moteur.pasJeu(COMMANDE.RIEN, COMMANDE.RIEN);
		Assert.assertTrue(true);
	}

	@Test
	public void testPasJeu2() {
		moteur.init(20, 4, 10, 100);
		moteur.pasJeu("Alex", COMMANDE.RIEN);
		Assert.assertTrue(true);
	}

	@Test
	public void testPasJeu3() {
		moteur.init(20, 4, 10, 100);
		moteur.pasJeu("Ryan", COMMANDE.RIEN);
		Assert.assertTrue(true);
	}

	@Test
	public void testPasJeu4() {
		try {
			moteur.init(20, 4, 10, 100);
			moteur.pasJeu("autre", COMMANDE.RIEN);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

}

package tests.abstrait;

import implem.ObjetEquipable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.PersonnageService;
import contracts.base.PreconditionError;

public abstract class AbstractTestPersonnage {
	private PersonnageService perso;

	protected AbstractTestPersonnage() {
		perso = null;
	}

	protected final PersonnageService getPerso() {
		return perso;
	}

	protected final void setPerso(PersonnageService perso) {
		this.perso = perso;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		perso = null;
	}

	// /////////////////////////
	// ///////// PRE ///////////
	@Test
	public void testInit() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		Assert.assertTrue(true);
	}

	@Test
	public void testInitFai1() {
		try {
			perso.init("Alex", -15, 50, 10, 100, 1664);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	//
	//
	// Test init
	@Test
	public void testInitFai2() {
		try {
			perso.init("Alex", 15, -50, 10, 100, 1664);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFai3() {
		try {
			perso.init("Alex", 15, 50, -10, 100, 1664);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFai4() {
		try {
			perso.init("Alex", 15, 50, 10, -100, 1664);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail5() {
		try {
			perso.init("Alex", 15, 50, 10, 100, -1664);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail6() {
		try {
			perso.init("", 15, 50, 10, 100, 1664);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	//
	//
	// Test retraitPdv
	@Test
	public void testRetraitPdv() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		perso.retraitPdv(50);
		Assert.assertTrue(true);
	}

	@Test
	public void testRetraitPdvFail() {
		try {
			perso.init("Alex", 15, 50, 10, 100, 1664);
			perso.retraitPdv(-50);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	//
	//
	// Test depotPdv
	@Test
	public void testDepotPdv() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		perso.depotPdv(50);
		Assert.assertTrue(true);
	}

	@Test
	public void testDepotPdvFail() {
		try {
			perso.init("Alex", 15, 50, 10, 100, 1664);
			perso.depotPdv(-50);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	//
	//
	// Test depotArgent
	@Test
	public void testDepotArgent() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		perso.depotArgent(50);
		Assert.assertTrue(true);
	}

	@Test
	public void testDepotArgentFail() {
		try {
			perso.init("Alex", 15, 50, 10, 100, 1664);
			perso.depotArgent(-50);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	//
	//
	// Test retraitArgent
	@Test
	public void testRetraitArgent1() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		perso.depotArgent(100);
		perso.retraitArgent(50);
		Assert.assertTrue(true);
	}

	@Test
	public void testRetraitArgent2() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		perso.depotArgent(50);
		perso.retraitArgent(50);
		Assert.assertTrue(true);
	}

	@Test
	public void testRetraitArgentFail1() {
		try {
			perso.init("Alex", 15, 50, 10, 100, 1664);
			perso.retraitArgent(50);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testRetraitArgentFail2() {
		try {
			perso.init("Alex", 15, 50, 10, 100, 1664);
			perso.retraitArgent(-50);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	//
	//
	// Test ramasser
	@Test
	public void testRamasser() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		ObjetEquipable obj = new ObjetEquipable();
		obj.init("baton", 300);
		perso.ramasser(obj);
		Assert.assertTrue(true);
	}

	@Test
	public void testRamasserFail() {
		try {
			perso.init("Alex", 15, 50, 10, 100, 1664);
			ObjetEquipable obj = new ObjetEquipable();
			obj.init("baton", 300);
			perso.ramasser(obj);
			ObjetEquipable obj2 = new ObjetEquipable();
			obj2.init("bare a mine", 600);
			perso.ramasser(obj2);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	//
	//
	// Test jeter
	@Test
	public void testJeter() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		ObjetEquipable obj = new ObjetEquipable();
		obj.init("baton", 300);
		perso.ramasser(obj);
		perso.jeter();
		Assert.assertTrue(true);
	}

	@Test
	public void testJeterFail() {
		try {
			perso.init("Alex", 15, 50, 10, 100, 1664);
			perso.jeter();
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	// /////////////////////////
	// ///////// POST //////////
	public boolean checkInvariant() {
		if (perso.estVaincu())
			return perso.pointsDeVie() == 0;
		return perso.pointsDeVie() > 0;
	}

	@Test
	public void testPostInit() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		Assert.assertTrue(checkInvariant() && perso.nom().equals("Alex")
				&& perso.largeur() == 15 && perso.hauteur() == 50
				&& perso.profondeur() == 10 && perso.force() == 100
				&& perso.pointsDeVie() == 1664 && perso.sommeArgent() == 0
				&& !perso.estEquipe() && perso.bonus() == 100
				&& !perso.estPorte());
	}

	@Test
	public void testPostRetraitPdv() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		int pdv_atpre = perso.pointsDeVie();
		perso.retraitPdv(50);
		Assert.assertTrue(checkInvariant()
				&& perso.pointsDeVie() == pdv_atpre - 50);
	}

	@Test
	public void testPostDepotPdv() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		int pdv_atpre = perso.pointsDeVie();
		perso.depotPdv(50);
		Assert.assertTrue(checkInvariant()
				&& perso.pointsDeVie() == pdv_atpre + 50);
	}

	@Test
	public void testPostDepotArgent() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		int argent_atpre = perso.sommeArgent();
		perso.depotArgent(50);
		Assert.assertTrue(checkInvariant()
				&& perso.sommeArgent() == argent_atpre + 50);

	}

	@Test
	public void testPostRetraitArgent1() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		perso.depotArgent(100);
		int argent_atpre = perso.sommeArgent();
		perso.retraitArgent(50);
		Assert.assertTrue(checkInvariant()
				&& perso.sommeArgent() == argent_atpre - 50);
	}

	@Test
	public void testPostRamasser() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		ObjetEquipable obj = new ObjetEquipable();
		obj.init("baton", 300);
		perso.ramasser(obj);
		Assert.assertTrue(checkInvariant() && perso.estEquipe()
				&& perso.laChoseEquipee().equals(obj));
	}

	@Test
	public void testPostJeter() {
		perso.init("Alex", 15, 50, 10, 100, 1664);
		ObjetEquipable obj = new ObjetEquipable();
		obj.init("baton", 300);
		perso.ramasser(obj);
		perso.jeter();
		Assert.assertTrue(checkInvariant() && !perso.estEquipe());
	}
}

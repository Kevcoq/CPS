package tests.abstrait;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.ObjetEquipableService;
import contracts.base.PreconditionError;

public abstract class AbstractTestObjetEquipable extends AbstractTestChose {
	private ObjetEquipableService obj;

	protected AbstractTestObjetEquipable() {
		obj = null;
	}

	protected final ObjetEquipableService getObjet() {
		return obj;
	}

	protected final void setObjet(ObjetEquipableService objet) {
		this.obj = objet;
	}

	@Before
	public abstract void beforeTests();

	// ///////////////////////////////////////////////////////
	// /////////////// PRE //////////////////
	//
	//
	//
	// Init
	@Test
	public void testInit() {
		obj.init("CHAINEVELO", 500);
		Assert.assertTrue(true);
	}

	@Test
	public void testInitFail1() {
		try {
			obj.init("", 500);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail2() {
		try {
			obj.init("CHAINEVELO", -100);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	// ///////////////////////////////////////////////////////
	// /////////////// POST //////////////////
	@Test
	public void testPostInit() {
		obj.init("CHAINEVELO", 500);
		Assert.assertTrue(obj.nom().equals("CHAINEVELO") && obj.bonus() == 500
				&& !obj.estPorte());
	}
}

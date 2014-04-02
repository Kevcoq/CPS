package tests.abstrait;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.ObjetService;
import contracts.base.PreconditionError;

public abstract class AbstractTestObjet {
	private ObjetService obj;

	protected AbstractTestObjet() {
		obj = null;
	}

	protected final ObjetService getObjet() {
		return obj;
	}

	protected final void setObjet(ObjetService objet) {
		this.obj = objet;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		obj = null;
	}

	// ///////// PRE /////////////
	@Test
	public void testInit() {
		obj.init("DOLLAR");
		Assert.assertTrue(true);
	}

	@Test
	public void testInitFail() {
		try {
			obj.init("");
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	// ///////// POST /////////////
	@Test
	public void testPostInit() {
		obj.init("DOLLAR");
		Assert.assertTrue(obj.nom().equals("DOLLAR"));
	}

}

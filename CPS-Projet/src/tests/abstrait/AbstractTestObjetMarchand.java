package tests.abstrait;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.ObjetMarchandService;
import contracts.base.PreconditionError;

public abstract class AbstractTestObjetMarchand {
	private ObjetMarchandService obj;

	protected AbstractTestObjetMarchand() {
		obj = null;
	}

	protected final ObjetMarchandService getObjet() {
		return obj;
	}

	protected final void setObjet(ObjetMarchandService objet) {
		this.obj = objet;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		obj = null;
	}

	//
	//
	//
	// Init
	@Test
	public void testInit() {
		obj.init("PEPITEOR", 5000);
		Assert.assertTrue(true);
	}

	@Test
	public void testInitFail() {
		try {
			obj.init("", 5000);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFai2() {
		try {
			obj.init("PEPITEOR", 0);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	//
	// Vendre
	@Test
	public void testVendre() {
		obj.init("PEPITEOR", 5000);
		obj.vendre();
		Assert.assertTrue(true);
	}

	@Test
	public void testVendreFail() {
		try {
			obj.init("", 5000);
			obj.vendre();
			obj.vendre();
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

}

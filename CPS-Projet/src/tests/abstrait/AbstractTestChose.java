package tests.abstrait;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.ChoseService;
import contracts.base.PreconditionError;

public abstract class AbstractTestChose {
	private ChoseService chose;

	protected AbstractTestChose() {
		chose = null;
	}

	protected final ChoseService getChose() {
		return chose;
	}

	protected final void setChose(ChoseService chose) {
		this.chose = chose;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		chose = null;
	}

	// ///////////////////////////////////////////////////////
	// /////////////// PRE //////////////////
	//
	//
	//
	// Init
	@Test
	public void testInit() {
		chose.init(50);
		Assert.assertTrue(true);
	}

	@Test
	public void testInitFail() {
		try {
			chose.init(-50);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	// estRamasse
	@Test
	public void testEstRamasse() {
		chose.init(50);
		chose.estRamasse();
		Assert.assertTrue(true);
	}

	@Test
	public void testEstRamasseFail() {
		try {
			chose.init(50);
			chose.estRamasse();
			chose.estRamasse();
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	//
	//
	//
	// estJete
	@Test
	public void testEstJete() {
		chose.init(50);
		chose.estRamasse();
		chose.estJete();
		Assert.assertTrue(true);
	}

	@Test
	public void testEstJeteFail() {
		try {
			chose.init(50);
			chose.estJete();
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	// ///////////////////////////////////////////////////////
	// /////////////// POST //////////////////
	@Test
	public void testPostInit() {
		chose.init(50);
		Assert.assertTrue(chose.bonus() == 50 && !chose.estPorte());
	}

	@Test
	public void testPostEstRamasse() {
		chose.init(50);
		chose.estRamasse();
		Assert.assertTrue(chose.estPorte());
	}

	@Test
	public void testPostEstJete() {
		chose.init(50);
		chose.estRamasse();
		chose.estJete();
		Assert.assertTrue(!chose.estPorte());
	}

}

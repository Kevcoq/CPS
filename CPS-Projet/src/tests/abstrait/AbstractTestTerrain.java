package tests.abstrait;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.TerrainService;
import contracts.base.PreconditionError;

public abstract class AbstractTestTerrain {
	private TerrainService terrain;

	/**
	 * @return the Terrain
	 */
	public final TerrainService getTerrain() {
		return terrain;
	}

	/**
	 * @param Terrain
	 *            the Terrain to set
	 */
	public final void setTerrain(TerrainService Terrain) {
		this.terrain = Terrain;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		terrain = null;
	}

	// ///////////////////////////
	// ///////// PRE /////////////
	//
	//
	//
	// Init
	@Test
	public void testInit() {
		terrain.init(20, 4, 10);
		Assert.assertTrue(true);
	}

	@Test
	public void testInitFail1() {
		try {
			terrain.init(-20, 4, 10);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail2() {
		try {
			terrain.init(20, 4, -10);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail3() {
		try {
			terrain.init(20, -4, 10);
		} catch (PreconditionError e) {
			Assert.assertTrue(true);
		}
	}

	// ///////////////////////////
	// ///////// POST ////////////
	//
	//
	//
	// Init
	@Test
	public void testPostInit() {
		terrain.init(20, 4, 10);
		if (terrain.largeur() == 20 && terrain.hauteur() == 4
				&& terrain.profondeur() == 10) {
			for (int i = 0; i < terrain.largeur(); i++)
				for (int j = 0; j < terrain.profondeur(); j++)
					for (int k = 0; k < terrain.hauteur(); k++)
						if (terrain.getBloc(i, j, k) == null)
							Assert.assertTrue(false);
		} else
			Assert.assertTrue(false);
	}
}

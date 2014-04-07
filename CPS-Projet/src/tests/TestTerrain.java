package tests;

import implem.Terrain;
import tests.abstrait.AbstractTestTerrain;
import contracts.TerrainContract;

public class TestTerrain extends AbstractTestTerrain {

	@Override
	public void beforeTests() {
		setTerrain(new TerrainContract(new Terrain()));
	}

}

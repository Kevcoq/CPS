package tests;

import implem.Terrain;
import contracts.TerrainContract;
import tests.abstrait.AbstractTestTerrain;

public class TestTerrain extends AbstractTestTerrain {

	@Override
	public void beforeTests() {
		setTerrain(new TerrainContract(new Terrain()));
	}

}

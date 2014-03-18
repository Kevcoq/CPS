package decorators;

import services.BlocService;
import services.TerrainService;

public abstract class TerrainDecorator implements TerrainService {
	private TerrainService terrain;

	@Override
	public int largeur() {
		return terrain.largeur();
	}

	@Override
	public int hauteur() {
		return terrain.hauteur();
	}

	@Override
	public int profondeur() {
		return terrain.profondeur();
	}

	@Override
	public BlocService getBloc(int i, int j, int k) {
		return terrain.getBloc(i, j, k);
	}

	@Override
	public void init(int largeur, int hauteur, int profondeur) {
		terrain.init(largeur, hauteur, profondeur);
	}

}

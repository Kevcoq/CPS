package implem;

import services.BlocService;
import services.TerrainService;

public class Terrain implements TerrainService {
	private int largeur;
	private int hauteur;
	private int profondeur;
	private BlocService map[][][];

	@Override
	public int largeur() {
		return largeur;
	}

	@Override
	public int hauteur() {
		return hauteur;
	}

	@Override
	public int profondeur() {
		return profondeur;
	}

	@Override
	public BlocService getBloc(int i, int j, int k) {
		return map[i][j][k];
	}

	@Override
	public void init(int largeur, int hauteur, int profondeur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.profondeur = profondeur;
		// TODO
		for(int i = 0; i < largeur; i++);
	}

}

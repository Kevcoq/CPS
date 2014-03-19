package implem;

import services.BlocService;
import services.TerrainService;
import enumeration.TYPE_Bloc;
import enumeration.TYPE_Tresor;

/**
 * Implem du Terrain
 * 
 * @author Kevin & Quentin
 * 
 */
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

		// On parcourt la map
		for (int i = 0; i < largeur / Bloc.largeur; i++) {
			for (int j = 0; j < hauteur / Bloc.hauteur; j++) {
				for (int k = 0; k < profondeur / Bloc.profondeur; k++) {
					// on creer un bloc
					Bloc b = new Bloc();

					if (Math.random() < 0.15) {
						// on initialise le bloc en fosse
						b.init(TYPE_Bloc.FOSSE);
					} else if (Math.random() < 0.25) {
						// on initialise le bloc en vide + tresor
						TYPE_Tresor tresor[] = TYPE_Tresor.values();
						b.init(TYPE_Bloc.VIDE,
								tresor[(int) (Math.random() * tresor.length)]);
					} else {
						// on initialise le bloc en vide
						b.init(TYPE_Bloc.VIDE);
					}
					// on ajoute le bloc a la map
					map[i][j][k] = b;
				}
			}
		}
	}

}

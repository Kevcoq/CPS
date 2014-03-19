package implem;

import enumeration.TYPE_Bloc;
import enumeration.TYPE_Tresor;
import services.BlocService;

/**
 * Implem des Bloc
 * @author Kevin & Quentin
 *
 */
public class Bloc implements BlocService {
	public static final int largeur = 10, hauteur = 10, profondeur = 10;

	private TYPE_Bloc tBloc;
	private TYPE_Tresor tTresor;

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
	public TYPE_Bloc typeBloc() {
		return tBloc;
	}

	@Override
	public boolean aTresor() {
		return tTresor != TYPE_Tresor.RIEN;
	}

	@Override
	public TYPE_Tresor typeTresor() {
		return tTresor;
	}

	@Override
	public void init(TYPE_Bloc b, TYPE_Tresor t) {
		tBloc = b;
		tTresor = t;
	}

	@Override
	public void init(TYPE_Bloc b) {
		init(b, TYPE_Tresor.RIEN);
	}

	@Override
	public void ramasserTresor() {
		tTresor = TYPE_Tresor.RIEN;
	}

	@Override
	public void deposerTresor(TYPE_Tresor t) {
		tTresor = t;
	}

}

package decorators;

import enumeration.TYPE_Bloc;
import enumeration.TYPE_Tresor;
import services.BlocService;

/**
 * Decorator de Bloc
 * 
 * @author Kevin & Quentin
 * 
 */
public abstract class BlocDecorator implements BlocService {
	private BlocService bloc;

	public BlocDecorator(BlocService bloc) {
		super();
		this.bloc = bloc;
	}

	/**
	 * @return
	 * @see services.BlocService#largeur()
	 */
	public int largeur() {
		return bloc.largeur();
	}

	/**
	 * @return
	 * @see services.BlocService#hauteur()
	 */
	public int hauteur() {
		return bloc.hauteur();
	}

	/**
	 * @return
	 * @see services.BlocService#profondeur()
	 */
	public int profondeur() {
		return bloc.profondeur();
	}

	/**
	 * @return
	 * @see services.BlocService#typeBloc()
	 */
	public TYPE_Bloc typeBloc() {
		return bloc.typeBloc();
	}

	/**
	 * @return
	 * @see services.BlocService#aTresor()
	 */
	public boolean aTresor() {
		return bloc.aTresor();
	}

	/**
	 * @return
	 * @see services.BlocService#typeTresor()
	 */
	public TYPE_Tresor typeTresor() {
		return bloc.typeTresor();
	}

	/**
	 * @param b
	 * @param t
	 * @see services.BlocService#init(enumeration.TYPE_Bloc,
	 *      enumeration.TYPE_Tresor)
	 */
	public void init(TYPE_Bloc b, TYPE_Tresor t) {
		bloc.init(b, t);
	}

	/**
	 * @param b
	 * @see services.BlocService#init(enumeration.TYPE_Bloc)
	 */
	public void init(TYPE_Bloc b) {
		bloc.init(b);
	}

	/**
	 * 
	 * @see services.BlocService#ramasserTresor()
	 */
	public void ramasserTresor() {
		bloc.ramasserTresor();
	}

	/**
	 * @param t
	 * @see services.BlocService#deposerTresor(enumeration.TYPE_Tresor)
	 */
	public void deposerTresor(TYPE_Tresor t) {
		bloc.deposerTresor(t);
	}

}

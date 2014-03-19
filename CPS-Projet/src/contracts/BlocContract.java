package contracts;

import implem.Bloc;
import contracts.base.InvariantError;
import contracts.base.PostconditionError;
import services.BlocService;
import decorators.BlocDecorator;
import enumeration.TYPE_Bloc;
import enumeration.TYPE_Tresor;

/**
 * Contrat des Bloc
 * 
 * @author Kevin & Quentin
 * 
 */
public class BlocContract extends BlocDecorator {

	/**
	 * constructeur
	 * 
	 * @param bloc
	 *            une implem de bloc
	 */
	public BlocContract(BlocService bloc) {
		super(bloc);
	}

	private void checkInvariant() {

		// ** [invariants]
		// *** aTresor(B) =(min) typeTresor ≠ TYPE_Tresor.RIEN

		if (!((typeTresor() != TYPE_Tresor.RIEN && aTresor()) || (typeTresor() == TYPE_Tresor.RIEN && !aTresor())))
			throw new InvariantError("bloc");
	}

	@Override
	public void init(TYPE_Bloc b, TYPE_Tresor t) {
		super.init(b, t);

		checkInvariant();

		// *** [init]
		// **** largeur(init(b,t)) = 10
		// **** hauteur(init(b,t)) = 10
		// **** profondeur(init(b,t)) = 10
		// **** typeBloc(init(b,t)) = b
		// **** typeTresor(init(b,t)) = t

		if (!(largeur() == Bloc.largeur && hauteur() == Bloc.hauteur
				&& profondeur() == Bloc.profondeur && typeBloc() == b && typeTresor() == t))
			throw new PostconditionError("bloc -> init(b,t)");
	}

	@Override
	public void init(TYPE_Bloc b) {
		super.init(b);

		checkInvariant();

		// *** [init]
		// **** largeur(init(b,t)) = 10
		// **** hauteur(init(b,t)) = 10
		// **** profondeur(init(b,t)) = 10
		// **** typeBloc(init(b,t)) = b
		// **** typeTresor(init(b,t)) = t

		if (!(largeur() == Bloc.largeur && hauteur() == Bloc.hauteur
				&& profondeur() == Bloc.profondeur && typeBloc() == b && typeTresor() == TYPE_Tresor.RIEN))
			throw new PostconditionError("bloc -> init(b)");
	}
}

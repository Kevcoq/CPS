package contracts;

import implem.Bloc;
import services.BlocService;
import services.TerrainService;
import contracts.base.PostconditionError;
import contracts.base.PreconditionError;
import decorators.TerrainDecorator;

/**
 * Contrat du Terrain
 * 
 * @author Kevin & Quentin
 * 
 */
public class TerrainContract extends TerrainDecorator {

	/**
	 * Constructeur
	 * 
	 * @param terrain
	 *            une implem de terrain
	 */
	public TerrainContract(TerrainService terrain) {
		super(terrain);
	}

	// pre getBloc(T,i,j,k) require (0 ≤ i < (largeur(T)/Bloc::largeur())) ∧ (0
	// ≤ j < (profondeur(T)/Bloc::profondeur())) ∧ (0 ≤ k <
	// (hauteur(T)/Bloc::hauteur()))
	@Override
	public BlocService getBloc(int i, int j, int k) {
		if (!(0 <= i && i < largeur() / Bloc.largeur && 0 <= j
				&& j < profondeur() / Bloc.profondeur && 0 <= k && k < hauteur()
				/ Bloc.hauteur))
			throw new PreconditionError("terrain -> getBloc");

		return super.getBloc(i, j, k);
	}

	// pre init(largeur,hauteur,profondeur) require largeur > 0 ∧ hauteur > 0 ∧
	// profondeur > 0
	@Override
	public void init(int largeur, int hauteur, int profondeur) {
		if (!(largeur > 0 && hauteur > 0 && profondeur > 0))
			throw new PreconditionError("terrain -> init");

		super.init(largeur, hauteur, profondeur);

		// *** [init]
		// **** largeur(init(l,h,p)) = l
		// **** hauteur(init(l,h,p)) = h
		// **** profondeur(init(l,h,p)) = p
		// **** getBloc(init(l,h,p),x,y,z) ≠ null avec
		// ***** ∀x ∈ [0;(largeur(init(l,h,p))/Bloc::largeur())],
		// ***** ∀y ∈ [0;(profondeur(init(l,h,p))/Bloc::profondeur())],
		// ***** ∀z ∈ [0;(hauteur(init(l,h,p))/Bloc::hauteur())],

		// TODO les pour tout
		if (!(largeur == largeur() && hauteur == hauteur() && profondeur == profondeur()))
			throw new PostconditionError("terrain -> init");

	}

}

package contracts;

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

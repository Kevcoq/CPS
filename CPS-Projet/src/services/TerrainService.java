package services;

/**
 * Interface Terrain
 * 
 * @author Kevin & Quentin
 * 
 */
public interface TerrainService {
	/* Observator */
	/**
	 * 
	 * @return la largeur de la map
	 */
	public int largeur();

	/**
	 * 
	 * @return la hauteur de la map
	 */
	public int hauteur();

	/**
	 * 
	 * @return la profondeur de la map
	 */
	public int profondeur();

	/**
	 * 
	 * @param i
	 *            abscisse
	 * @param j
	 *            ordonnee
	 * @param k
	 *            profondeur
	 * @return le bloc correspondant dans le tbl
	 */
	// pre getBloc(T,i,j,k) require (0 ≤ i < (largeur(T)/Bloc::largeur())) ∧ (0
	// ≤ j < (profondeur(T)/Bloc::profondeur())) ∧ (0 ≤ k <
	// (hauteur(T)/Bloc::hauteur()))
	public BlocService getBloc(int i, int j, int k);

	/* Constructeur */
	/**
	 * 
	 * @param largeur
	 *            la largeur
	 * @param hauteur
	 *            la hauteur
	 * @param profondeur
	 *            la profondeur
	 */
	// pre init(largeur,hauteur,profondeur) require largeur > 0 ∧ hauteur > 0 ∧
	// profondeur > 0
	public void init(int largeur, int hauteur, int profondeur);

	// ** Observations :
	//
	// *** [init]
	// **** largeur(init(l,h,p)) = l
	// **** hauteur(init(l,h,p)) = h
	// **** profondeur(init(l,h,p)) = p
	// **** getBloc(init(l,h,p),x,y,z) ≠ null avec
	// ***** ∀x ∈ [0;(largeur(init(l,h,p))/Bloc::largeur())],
	// ***** ∀y ∈ [0;(profondeur(init(l,h,p))/Bloc::profondeur())],
	// ***** ∀z ∈ [0;(hauteur(init(l,h,p))/Bloc::hauteur())],

}
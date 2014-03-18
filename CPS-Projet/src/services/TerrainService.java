package services;

public interface TerrainService {
	public int largeur();
	
	public int hauteur();

	public int profondeur();
	
	// pre getBloc(T,i,j,k) require (0 ≤ i < (largeur(T)/Bloc::largeur())) ∧ (0 ≤ j < (profondeur(T)/Bloc::profondeur())) ∧ (0 ≤ k < (hauteur(T)/Bloc::hauteur()))
	public BlocService getBloc(int i, int j, int k);

	// pre init(largeur,hauteur,profondeur) require largeur > 0 ∧ hauteur > 0 ∧ profondeur > 0 
	public void init(int largeur, int hauteur, int profondeur);

/*
 ** Observations :

*** [init]
**** largeur(init(l,h,p)) = l
**** hauteur(init(l,h,p)) = h
**** profondeur(init(l,h,p)) = p
**** getBloc(init(l,h,p),x,y,z) ≠ null avec 
***** ∀x ∈ [0;(largeur(init(l,h,p))/Bloc::largeur())], 
***** ∀y ∈ [0;(profondeur(init(l,h,p))/Bloc::profondeur())], 
***** ∀z ∈ [0;(hauteur(init(l,h,p))/Bloc::hauteur())],
 */
}
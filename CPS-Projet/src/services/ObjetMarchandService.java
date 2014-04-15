package services;

public interface ObjetMarchandService extends /* refine */ ObjetService {
	
	/* Observators */
	
	public int prix();

	public boolean estVendu();

	
	/* Constructor */
	/**
	 * pre init(nom,prix) require nom ≠ "" ∧ prix > 0
	 * @param nom
	 * @param prix
	 */
	public void init(String nom, int prix);

	
	/* Operators */
	/**
	 * pre vendre(O) require ¬estVendu(O)
	 */
	public void vendre();
	
	/* Observations */
	
	// *** [init]
	// **** prix(init(n,p)) = p
	// **** estVendu(init(n,p)) = false
	//
	// *** [vendre]
	// **** estVendu(vendre(O)) = true

}

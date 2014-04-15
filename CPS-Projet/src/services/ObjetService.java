package services;

/**
 * Interface Objet
 * @author Kevin & Quentin
 *
 */
public interface ObjetService {
	
	/* Observators */
	/**
	 * @return nom
	 */
	public String nom();
	
	/* Constructor */
	/**
	 * pre init(nom) require nom ≠ ""
	 * @param nom
	 */
	public void init(String nom);

	/* Observations */

	// *** [init]
	// **** nom(init(n)) = n

}
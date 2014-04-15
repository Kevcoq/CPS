package services;

/**
 * Une chose
 * 
 * @author Kevin & Quentin
 * 
 */
public interface ChoseService {

	/* Observators */
	
	/**
	 * Le bonus de frappe 
	 * @return bonus de frappe
	 */
	public int bonus();

	/**
	 * @return vrai si on est porte
	 */
	public boolean estPorte();

	
	/* Constructors */
	
	/**
	 * init
	 * pre init(C, bonus) require bonus > 0 
	 */
	public void init(int b);

	
	/* Operators */
	
	/**
	 * statut ramasse
	 * pre estRamasse(C) require ¬estPorte(C)
	 */
	public void estRamasse();

	/**
	 * statut classique
	 * pre estJete(C) require estPorte(C)
	 */
	public void estJete();

	
	/* Observations */
	
//	*** [init]
//	**** bonus(init(b)) = b
//	**** estPorte(init(b)) = false
//
//	*** [estRamasse]
//	**** estPorte(estRamasse(C)) = true
//
//	*** [estJete]
//	**** estPorte(estJete(C)) = false

}
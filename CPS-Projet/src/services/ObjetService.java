package services;

/**
 * Interface Objet
 * @author Kevin & Quentin
 *
 */
public interface ObjetService {
	// observators :
	public String nom();
	
	// constructors :
	public void init(String nom);
}

// * Objet
// ** service : Objet
// ** types : String
//
//
// ** observators :
// **** const nom : [Objet] → String
//
//
// ** Constructors :
// **** init : String → [Objet]
// ***** pre init(nom) require nom ≠ ""
//
//
// ** Observations :
// *** [init]
// **** nom(init(n)) = n

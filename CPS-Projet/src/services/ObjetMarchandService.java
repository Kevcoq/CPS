package services;

public interface ObjetMarchandService extends ObjetService {
	// observator :
	public int prix();

	public boolean estVendu();

	// constructor :
	public void init(int prix);

	// operator :
	public void vendre();
}


// * ObjetMarchand
// ** service : ObjetMarchand
// ** refine : [[file:objet.org][Objet]]
// ** types : String, int, boolean
//
//
// ** observators :
// **** const prix : [ObjetMarchand] → int
// **** estVendu : [ObjetMarchand] → boolean
//
//
// ** Constructors :
// **** init : String × int → [ObjetMarchand]
// ***** pre init(nom,prix) require nom ≠ "" ∧ prix > 0
//
//
// ** Operators :
// **** vendre : [ObjetMarchand] → [ObjetMarchand]
// ***** pre vendre(O) require ¬estVendu(O)
//
//
// ** Observations :
// *** [init]
// **** prix(init(n,p)) = p
// **** estVendu(init(n,p)) = false
//
// *** [vendre]
// **** estVendu(vendre(O)) = true
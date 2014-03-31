package services;

import enumeration.COMMANDE;
import enumeration.RESULTAT;

public interface MoteurJeuService {
	// observator :
	public int maxPasJeu();

	public int pasJeuCourant();

	public boolean estFini();

	public RESULTAT resultatFinal();

	public GestionCombatService combat();

	// construtors :
	public void init(int largeur, int hauteur, int profondeur, int maxPas);

	// operator :
	public void pasJeu(COMMANDE cmd);

}

// * MoteurJeu
// ** service : MoteurJeu
// ** use : [[file:gestionCombat.org][GestionCombat]]
// ** types : String, int, boolean, enum FIN {GAGNEE, PERDUE, NULLE}, enum
// COMMANDE {RIEN, GAUCHE, DROITE, HAUT, BAS, SAUTER, FRAPPE, JETER, RAMASSER}
//
// ** observators :
// **** const maxPasJeu : [MoteurJeu] → int
// **** pasJeuCourant : [MoteurJeu] → int
// **** estFini : [MoteurJeu] → boolean
// **** resultatFinal : [MoteurJeu] → FIN
// ***** pre resultatFinal(M) require estFini(M)
// **** combat : [MoteurJeu] → GestionCombat
//
// ** Constructors :
// **** init : int × int × int × int → [MoteurJeu]
// ***** pre init(largeur, hauteur, profondeur, maxPas) require largeur > 0 ∧
// hauteur > 0 ∧ profondeur > 0 ∧ maxPas > 0
//
// ** Operators :
// **** pasJeu : [MoteurJeu] × COMMANDE × COMMANDE → [MoteurJeu]
// ***** pre pasJeu(M, comAlex, comRyan) require ¬estFini(M) ∧ comAlex ∈
// COMMANDE ∧ comRyan ∈ COMMANDE
//
// **** pasJeu : [MoteurJeu] × String × COMMANDE → [MoteurJeu]
// ***** pre pasJeu(M, nom, com) require ¬estFini(M) ∧ nom = ("Alex" ∨ "Ryan") ∧
// com ∈ COMMANDE
//
//
//
//
// ** Observations :
// *** [invariants]
// **** 0 ⩽ pasJeuCourant(M) ⩽ maxPasJeu(M)
// **** estFini(M) =min (Personnage::estVaincu(GestionCombat::alex(combat(M))) ∧
// Personnage::estVaincu(GestionCombat::ryan(combat(M)))) ∨
// Personnage::estVaincu(GestionCombat::slick(combat(M))) ∨
// pasJeuCourant(M)=maxPasJeu(M))
// **** resultatFinal(M) =min
// ***** GAGNEE si Personnage::estVaincu(GestionCombat::slick(combat(M))) ∧
// ¬(Personnage::estVaincu(GestionCombat::alex(combat(M))) ∧
// Personnage::estVaincu(GestionCombat::ryan(combat(M))))
// ***** PERDUE si ¬Personnage::estVaincu(GestionCombat::slick(combat(M))) ∧
// (Personnage::estVaincu(GestionCombat::alex(combat(M))) ∧
// Personnage::estVaincu(GestionCombat::ryan(combat(M))))
// ***** NULLE sinon
//
// *** [init]
// **** maxPasJeu(init(l,h,p,m))=m
// **** pasJeuCourant(init(l,h,p,m))=0
// **** combat(init(l,h,p,m))=GestionCombat::init(l,h,p)
//
// *** [pasJeu]
// **** pasJeuCourant(pasJeu(M,cA,cR))=pasJeuCourant(M) +1
// ****
// combat(pasJeu(M,cA,cR))=GestionCombat::gerer(combat(M),Map<String,COMMANDE>)
// ***** avec put("Alex", cA) ∧ put("Ryan", cR) ∧ ∀i
// [0...GestionCombat::mPerso(combat(M)).size()-2] put(name, RandomCmd)
//
// **** pasJeuCourant(pasJeu(M,n,c))=pasJeuCourant(M) +1
// **** combat(pasJeu(M,n,c))=
// ***** si (n == "Alex") alors combat(pasJeu(M,c,RIEN))
// ***** sinon combat(pasJeu(M,RIEN,c))

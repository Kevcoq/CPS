package services;

import enumeration.COMMANDE;
import enumeration.RESULTAT;

/**
 * Interface MoteurJeu
 * @author Kevin & Quentin
 *
 */
public interface MoteurJeuService {
	
	/* Observators */
	
	public int maxPasJeu();

	public int pasJeuCourant();

	public boolean estFini();

	/**
	 * pre resultatFinal(M) require estFini(M)
	 * @return
	 */
	public RESULTAT resultatFinal();

	public GestionCombatService combat();
	
	

	/* Construtors */
	
	/**
	 * pre init(largeur, hauteur, profondeur, maxPas) require largeur > 0 
	 * ∧ hauteur > 0 ∧ profondeur > 0 ∧ maxPas > 0
	 * @param largeur
	 * @param hauteur
	 * @param profondeur
	 * @param maxPas
	 */
	public void init(int largeur, int hauteur, int profondeur, int maxPas);

	
	/* Operators */
	
	/**
	 * pre pasJeu(M, comAlex, comRyan) require ¬estFini(M) 
	 * ∧ comAlex ∈ COMMANDE ∧ comRyan ∈ COMMANDE
	 * @param cmdAlex
	 * @param cmdRyan
	 */
	public void pasJeu(COMMANDE cmdAlex, COMMANDE cmdRyan);

	/**
	 * pre pasJeu(M, nom, com) require ¬estFini(M) 
	 * ∧ nom = ("Alex" ∨ "Ryan") ∧ com ∈ COMMANDE
	 * @param nom
	 * @param cmd
	 */
	public void pasJeu(String nom, COMMANDE cmd);

	/* Observations */
	
	// *** [invariants]
	//
	// **** 0 ⩽ pasJeuCourant(M) ⩽ maxPasJeu(M)
	//
	// **** estFini(M) =min 
	// 		(Personnage::estVaincu(GestionCombat::mPerso(combat(M)).get("Alex")) 
	// 		∧ Personnage::estVaincu(GestionCombat::mPerso(combat(M)).get("Ryan")) 
	//		∨ Personnage::estVaincu(GestionCombat::mPerso(combat(M)).get("Slick")) 
	//		∨ pasJeuCourant(M)=maxPasJeu(M))
	//
	// **** resultatFinal(M) =min
	// 		GAGNEE si 
	// 			Personnage::estVaincu(GestionCombat::mPerso(combat(M).get("Slick"))) 
	//			∧ ¬(Personnage::estVaincu(GestionCombat::mPerso(combat(M)).get("Alex"))
	//			∧ Personnage::estVaincu(GestionCombat::mPerso(combat(M)).get("Ryan")))
	// 		PERDUE si 
	//			¬Personnage::estVaincu(GestionCombat::mPerso(combat(M)).get("Slick")) 
	//			∧ (Personnage::estVaincu(GestionCombat::mPerso(combat(M)).get("Alex")) 
	//			∧ Personnage::estVaincu(GestionCombat::mPerso(combat(M)).get("Ryan")))
	//  	NULLE sinon
	//
	//
	// *** [init]
	// **** maxPasJeu(init(l,h,p,m))=m
	// **** pasJeuCourant(init(l,h,p,m))=0
	// **** combat(init(l,h,p,m))=GestionCombat::init(l,h,p)
	//
	//
	// *** [pasJeu]
	// **** pasJeuCourant(pasJeu(M,cA,cR))=pasJeuCourant(M) + 1
	// **** combat(pasJeu(M,cA,cR)) = 
	//		GestionCombat::gerer(combat(M),Map<String,COMMANDE>)
	// 			avec put("Alex", cA) ∧ put("Ryan", cR) 
	//			∧ ∀i ∈ [0...GestionCombat::mPerso(combat(M)).size()-3] 
	//				put(name, RandomCmd)
	// 			∧ si (Math.random() < 0.1) alors put(randomName(), randomCmd())
	//
	// **** pasJeuCourant(pasJeu(M,n,c))=pasJeuCourant(M) +1
	// **** combat(pasJeu(M,n,c)) = 
	//		GestionCombat::gerer(combat(M),Map<String,COMMANDE>)
	//			avec put(n, c) 
	//			∧ ∀i ∈ [0...GestionCombat::mPerso(combat(M)).size()-2] 
	//					put(name, randomCmd()) 
	//			∧ si (Math.random() < 0.1) alors put(randomName(), randomCmd())

}
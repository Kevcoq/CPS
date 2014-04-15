package services;

/**
 * Interface Personnage
 * 
 * @author Kevin & Quentin
 * 
 */
public interface PersonnageService extends /* refine */ ChoseService {
	/* Observators */
	
	/**
	 * @return le nom du personnage
	 */
	public String nom();

	/**
	 * @return la largeur du personnage
	 */
	public int largeur();

	/**
	 * @return la hauteur du personnage
	 */
	public int hauteur();

	/**
	 * @return la profondeur du personnage
	 */
	public int profondeur();

	/**
	 * @return la force du personnage
	 */
	public int force();

	/**
	 * @return les pts de vie du personnage
	 */
	public int pointsDeVie();

	/**
	 * @return vrai si le personnage est mort
	 */
	public boolean estVaincu();

	/**
	 * @return l'argent du personnage
	 */
	public int sommeArgent();

	/**
	 * @return vrai si il detient un objet
	 */
	public boolean estEquipe();

	/**
	 * pre laChoseEquipee(P) require estEquipe(P)
	 * @return rend l'objet equipee
	 */
	public ChoseService laChoseEquipee();

	
	/* Constructor */
	/**
	 * pre init(nom,largeur,hauteur,profondeur,force,pointsVie) require 
	 * nom ≠ "" ∧ largeur > 0 ∧ hauteur > 0 ∧ profondeur > 0 
	 * ∧ 0 < force < pointsVie
	 * @param nom son nom != vide
	 * @param largeur sa largeur > 0
	 * @param hauteur sa hauteur > 0
	 * @param profondeur sa profondeur > 0
	 * @param force sa force > 0 && < pdv
	 * @param pdv ses pdv
	 */
	public void init(String nom, int largeur, int hauteur, int profondeur,
			int force, int pdv);

	/* Operators */
	
	/**
	 * retire s pdv au personnage
	 * 
	 * pre retraitPdv(P,s) require ¬estVaincu(P) ∧ s > 0
	 * @param s nbr de pdv a retirer
	 */
	public void retraitPdv(int s);

	/**
	 * rend s pdv au personnage
	 * 
	 * pre depotPdv(P,s) require ¬estVaincu(P) ∧ s > 0
	 * @param s nbr de pdv a rendre
	 */
	public void depotPdv(int s);

	/**
	 * retire s $ au personnage
	 * 
	 * pre retraitArgent(P,s) require ¬estVaincu(P) ∧ s > 0 ∧ s < sommeArgent(P)
	 * @param s nbr de $ a retirer
	 */
	public void retraitArgent(int s);

	/**
	 * ajouter s $ au personnage
	 * 
	 * pre depotArgent(P,s) require ¬estVaincu(P) ∧ s > 0 ∧ ¬estPorte(P)
	 * @param s nbr de $ a ajouter
	 */
	public void depotArgent(int s);

	/**
	 * ramasse un objet
	 * 
	 * pre ramasser(P,o) require ¬estVaincu(P) ∧ ¬estEquipe(P) ∧ ¬estPorte(P)
	 * @param o l'objet
	 */
	public void ramasser(ChoseService o);

	/**
	 * jete l'objet detenu, requiert d'avoir un objet
	 * 
	 * pre jeter(P) require ¬estVaincu(P) ∧ estEquipe(P) ∧ ¬estPorte(P)
	 */
	public void jeter();

	
	/* Observations */
	
//	*** [invariants]
//	**** estVaincu(P) =(min) pointsDeVie(P) ≤ 0
//
//	*** [init]
//	**** nom(init(n,l,h,p,f,pv)) = n
//	**** largeur(init(n,l,h,p,f,pv)) = l
//	**** hauteur(init(n,l,h,p,f,pv)) = h
//	**** profondeur(init(n,l,h,p,f,pv)) = p
//	**** force(init(n,l,h,p,f,pv)) = f
//	**** pointsDeVie(init(n,l,h,p,f,pv)) = pv
//	**** sommeArgent(init(n,l,h,p,f,pv)) = 0
//	**** estEquipe(init(n,l,h,p,f,pv)) = false
//
//	*** [retraitPdv]
//	**** pointsDeVie(retraitPdv(P,s)) = pointsDeVie(P) - s
//	**** sommeArgent(retraitPdv(P,s)) = sommeArgent(P)
//	**** estEquipe(retraitPdv(P,s)) = estEquipe(P)
//	**** laChoseEquipee(retraitPdv(P,s)) = laChoseEquipee(P)
//
//	*** [depotPdv]
//	**** pointsDeVie(depotPdv(P,s)) = pointsDeVie(P) + s
//	**** sommeArgent(depotPdv(P,s)) = sommeArgent(P)
//	**** estEquipe(depotPdv(P,s)) = estEquipe(P)
//	**** laChoseEquipee(depotPdv(P,s)) = laChoseEquipee(P)
//
//	*** [retraitArgent]
//	**** sommeArgent(retraitArgent(P,s)) = sommeArgent(P) - s
//	**** pointsDeVie(retraitArgent(P,s)) = pointsDeVie(P)
//	**** estEquipe(retraitArgent(P,s)) = estEquipe(P)
//	**** laChoseEquipee(retraitArgent(P,s)) = laChoseEquipee(P)
//
//	*** [depotArgent]
//	**** sommeArgent(depotArgent(P,s)) = sommeArgent(P) + s
//	**** pointsDeVie(depotArgent(P,s)) = pointsDeVie(P)
//	**** estEquipe(depotArgent(P,s)) = estEquipe(P)
//	**** laChoseEquipee(depotArgent(P,s)) = laChoseEquipee(P)
//
//	*** [ramasser]
//	**** estEquipe(ramasser(P,c)) = true
//	**** laChoseEquipee(ramasser(P,c)) = c
//	**** sommeArgent(ramasser(P,c)) = sommeArgent(P)
//	**** pointsDeVie(ramasser(P,c)) = pointsDeVie(P)
//
//	*** [jeter]
//	**** estEquipe(jeter(P)) = false
//	**** sommeArgent(jeter(P)) = sommeArgent(P)
//	**** pointsDeVie(jeter(P)) = pointsDeVie(P)


}
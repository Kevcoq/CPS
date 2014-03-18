package services;

public interface PersonnageService {
	/* Observator */
	public String nom();

	public int largeur();

	public int hauteur();

	public int profondeur();

	public int force();

	public int pointsDeVie();

	public boolean estVaincu();

	public int sommeArgent();

	public boolean estEquipe();
	
	// pre laChoseEquipee(P) require estEquipe(P)
	public ObjetService laChoseEquipee();
	

	/* Constructeur */
	// pre init(nom,largeur,hauteur,profondeur,force,pointsVie) require nom ≠ ""
	// ∧ largeur > 0 ∧ hauteur > 0 ∧ profondeur > 0 ∧ 0 < force < pointsVie
	public void init(String nom, int largeur, int hauteur, int profondeur,
			int force, int pdv);

	/* Operator */
	// pre retraitPdv(P,s) require ¬estVaincu(P) ∧ s > 0
	public void retraitPdv(int s);

	// pre depotPdv(P,s) require ¬estVaincu(P) ∧ s > 0
	public void depotPdv(int s);
	
	// pre retraitArgent(P,s) require ¬estVaincu(P) ∧ s > 0     ∧   s < sommeArgent(P)
	public void retraitArgent(int s);

	// pre depotArgent(P,s) require ¬estVaincu(P) ∧ s > 0
	public void depotArgent(int s);

	// pre ramasser(P,o) require ¬estVaincu(P) ∧ ¬estEquipe(P)
	public void ramasser(ObjetService o);
	
	// pre jeter(P) require ¬estVaincu(P) ∧ estEquipe(P)
	public void jeter();
	

/*

** Observations :
*** [invariants]
**** estVaincu(P) =(min) pointsDeVie(P) ≤ 0

*** [init]
**** nom(init(n,l,h,p,f,pv)) = n
**** largeur(init(n,l,h,p,f,pv)) = l
**** hauteur(init(n,l,h,p,f,pv)) = h
**** profondeur(init(n,l,h,p,f,pv)) = p
**** force(init(n,l,h,p,f,pv)) = f
**** pointsDeVie(init(n,l,h,p,f,pv)) = pv
**** sommeArgent(init(n,l,h,p,f,pv)) = 0
**** estEquipe(init(n,l,h,p,f,pv)) = false


*** [retraitPdv]
**** pointsDeVie(retraitPdv(P,s)) = pointsDeVie(P) - s
**** sommeArgent(retraitPdv(P,s)) = sommeArgent(P)
**** estEquipe(retraitPdv(P,s)) = estEquipe(P)
**** laChoseEquipee(retraitPdv(P,s)) = laChoseEquipee(P)

*** [depotPdv]
**** pointsDeVie(depotPdv(P,s)) = pointsDeVie(P) + s
**** sommeArgent(depotPdv(P,s)) = sommeArgent(P)
**** estEquipe(depotPdv(P,s)) = estEquipe(P)
**** laChoseEquipee(depotPdv(P,s)) = laChoseEquipee(P)

*** [retraitArgent]
**** sommeArgent(retraitArgent(P,s)) = sommeArgent(P) - s
**** pointsDeVie(retraitArgent(P,s)) = pointsDeVie(P)
**** estEquipe(retraitArgent(P,s)) = estEquipe(P)
**** laChoseEquipee(retraitArgent(P,s)) = laChoseEquipee(P)

*** [depotArgent]
**** sommeArgent(depotArgent(P,s)) = sommeArgent(P) + s
**** pointsDeVie(depotArgent(P,s)) = pointsDeVie(P)
**** estEquipe(depotArgent(P,s)) = estEquipe(P)
**** laChoseEquipee(depotArgent(P,s)) = laChoseEquipee(P)

*** [ramasser]
**** estEquipe(ramasser(P,o)) = true
**** laChoseEquipee(ramasser(P,o)) = o
**** sommeArgent(ramasser(P,o)) = sommeArgent(P)
**** pointsDeVie(ramasser(P,o)) = pointsDeVie(P)

*** [jeter]
**** estEquipe(jeter(P)) = false
**** sommeArgent(jeter(P)) = sommeArgent(P)
**** pointsDeVie(jeter(P)) = pointsDeVie(P)

*/
 
      
}

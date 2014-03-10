package services;

public interface PersonnageService {
	/* Observator */
	public String nom();
	public int largeur();
	public int hauteur();
	public int force();
	public int pointsDeVie();
	public boolean estVaincu();

	/* Constructeur */
	// pre init(nom,largeur,hauteur,force,pointsVie) require nom = "" ∧ largeur%2=1 ∧ hauteur%2=1 ∧ 0 < force < pointsVie
	public void init(String nom, int largeur, int hauteur, int force, int pdv);

	/* Operator */
	// pre retrait(P,s) require ¬estVaincu(P) ∧ s>0
	public void retrait(int s);

	/*
Observations :
[invariants]
                       min
       estVaincu(P) = pointsDeVie(P) ≤ 0
[init]
       nom(init(n,l,h,f,p))=n
       largeur(init(n,l,h,f,p))=l
       hauteur(init(n,l,h,f,p))=h
       force(init(n,l,h,f,p))=f
       pointsDeVie(init(n,l,h,f,p))=p
[retrait]
       pointsDeVie(retrait(P,s))=pointsDeVie(P) -s
	 */
}

//package services;
//
//import enumeration.COMMANDE;
//
//public interface GestionCombatService {
//	public int largeur();
//
//	public int hauteur();
//
//	public PersonnageService alex();
//
//	public PersonnageService ryan();
//
//	// pre estFrappé(C,id) require id="Alex" ∨ id="Ryan"
//	public boolean estFrappe(String nom);
//
//	// pre estGele(C,id) require id="Alex" ∨ id="Ryan"
//	public boolean estGele(String nom);
//
//	// pre position(C,id) require id="Alex" ∨ id="Ryan"
//	public int[] position(String nom);
//
//	// pre collision(C,id) require id="Alex" ∨ id="Ryan"
//	public boolean collision(String nom);
//
//	public void init(int largeur, int hauteur);
//
//	public void gerer(String nom, COMMANDE cmd);
//
//	// TODO post
//}

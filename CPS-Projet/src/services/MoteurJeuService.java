//package services;
//
//import enumeration.COMMANDE;
//import enumeration.RESULTAT;
//
//public interface MoteurJeuService {
//	public int maxPasJeu();
//
//	public int pasJeuCourant();
//
//	public boolean estFini();
//
//	// pre resultatFinal(M) require estFini(M)
//	public RESULTAT resultatFinal();
//
//	public GestionCombatService combat();
//
//	// pre init(largeur,hauteur,maxPas) require largeur≥ 256 ∧ hauteur≥ 240 ∧
//	// maxPas≥ 0
//	public void init(int largeur, int hauteur, int maxPas);
//
//	// pre pasJeu(M,comGuile,comRyu) require ¬estFini(M)
//	public void pasJeu(COMMANDE cmd);
//
//	// TODO observation
//}

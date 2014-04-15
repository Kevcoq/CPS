package services;

/**
 * Interface Gangster
 * @author Kevin & Quentin
 *
 */
public interface GangsterService extends /* refine */ PersonnageService {

	/* Constructors */
	
	/**
	 * pre init(nom,largeur,hauteur,profondeur,force,pointsVie) require 
	 * nom ≠ "" 
	 * ∧ largeur > 0 
	 * ∧ hauteur > 0 
	 * ∧ profondeur > 0 
	 * ∧ 0 < force < pointsVie
	 */
	public void init (String nom, int largeur, int hauteur, int profondeur,
			int force, int pdv); 
	
}

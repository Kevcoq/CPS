package services;

import enumeration.TYPE_Bloc;
import enumeration.TYPE_Tresor;

/**
 * Interface Bloc
 * 
 * @author Kevin & Quentin
 * 
 */
public interface BlocService {
	
	/* Observators */
	
	/** 
	 * @return la largeur d'un bloc
	 */
	public int largeur();

	/**
	 * @return la hauteur d'un bloc
	 */
	public int hauteur();

	/**
	 * @return la profondeur d'un bloc
	 */
	public int profondeur();

	/**
	 * @return le type du bloc {VIDE, FOSSE}
	 */
	public TYPE_Bloc typeBloc();

	/**
	 * @return vrai si le bloc a un tresor
	 */
	public boolean aTresor();

	/**
	 * pre typeTresor(B) require aTresor(B)
	 * @return le type du Tresor {RIEN, DOLLAR, ARME}
	 */
	public TYPE_Tresor typeTresor();

	
	/* Constructor */
	
	/**
	 * Construit un bloc avec un tresor
	 * 
	 * @param b type du bloc
	 * @param t type du tresor
	 */
	public void init(TYPE_Bloc b, TYPE_Tresor t);

	/**
	 * Construit un bloc sans tresor
	 * 
	 * @param b type du bloc
	 */
	public void init(TYPE_Bloc b);

	
	/* Operators */
	
	/**
	 * ramasser le tresor, requier aTresor
	 * 
	 * pre ramasserTresor(B) require aTresor(B)
	 */
	public void ramasserTresor();

	/**
	 * dépose le tresor
	 * 
	 * pre deposerTresor(B,o) require ¬aTresor(B)
	 * @param t le type du tresor a deposer
	 */
	public void deposerTresor(TYPE_Tresor t);

	
	/* Observations */
	
//	*** [invariants]
//	**** aTresor(B) =(min) typeTresor ≠ TYPE_Tresor.RIEN
//
//	*** [init]
//	**** largeur(init(b,t)) = 40
//	**** hauteur(init(b,t)) = 100
//	**** profondeur(init(b,t)) = 40
//	**** typeBloc(init(b,t)) = b
//	**** typeTresor(init(b,t)) = t
//
//	**** largeur(init(b)) = 10
//	**** hauteur(init(b)) = 10
//	**** profondeur(init(b)) = 10
//	**** typeBloc(init(b)) = b
//	**** typeTresor(init(b)) = TYPE_Tresor.RIEN
//
//	*** [ramasserTresor]
//	**** typeTresor(ramasserTresor(B)) = TYPE_Tresor.RIEN
//
//	*** [deposerTresor]
//	**** typeTresor(deposerTresor(B,t)) = Objet::type(t)
	
}

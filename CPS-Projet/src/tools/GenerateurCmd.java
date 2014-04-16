package tools;

import enumeration.COMMANDE;

/**
 * Generateur de commande
 * 
 * @author Kevin & Quentin
 * 
 */
public class GenerateurCmd {
	// /**
	// * genere des commandes aleatoirement dans l'intervalle donne actuellement
	// * RIEN
	// *
	// * @return RIEN
	// */
	// public static COMMANDE genererCmd() {
	// // TODO inverser quand fctionnelle
	// // COMMANDE[] cmds = COMMANDE.values();
	// // COMMANDE[] cmds = { COMMANDE.RIEN, COMMANDE.GAUCHE, COMMANDE.DROITE,
	// // COMMANDE.HAUT, COMMANDE.BAS, COMMANDE.SAUTER, COMMANDE.FRAPPE };
	//
	// // bisounours mode
	// COMMANDE[] cmds = { COMMANDE.RIEN };
	//
	// return cmds[(int) (Math.random() * cmds.length)];
	// }

	/**
	 * genere des commandes en fonction de certains ratio
	 * 
	 * @return une commande
	 */
	public static COMMANDE genererCmd() {
		if (Math.random() < 0.8) {
			COMMANDE[] cmds = { COMMANDE.GAUCHE, COMMANDE.DROITE,
					COMMANDE.HAUT, COMMANDE.BAS };
			return cmds[(int) (Math.random() * cmds.length)];
		} else if (Math.random() < 0.8) {
			return COMMANDE.FRAPPE;
		} else {
			COMMANDE[] cmds = { COMMANDE.RAMASSER, COMMANDE.JETER };
			return cmds[(int) (Math.random() * cmds.length)];
		}
	}
}

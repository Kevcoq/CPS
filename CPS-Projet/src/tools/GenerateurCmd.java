package tools;

import enumeration.COMMANDE;

public class GenerateurCmd {
	public static COMMANDE genererCmd() {
		// TODO inverser quand fctionnelle
		// COMMANDE[] cmds = COMMANDE.values();
		// COMMANDE[] cmds = { COMMANDE.RIEN, COMMANDE.GAUCHE, COMMANDE.DROITE,
		// COMMANDE.HAUT, COMMANDE.BAS, COMMANDE.SAUTER, COMMANDE.FRAPPE };

		// bisounours mode
		COMMANDE[] cmds = { COMMANDE.RIEN };

		return cmds[(int) (Math.random() * cmds.length)];
	}
}

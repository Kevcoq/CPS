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
	//
	// public static COMMANDE genererCmd() {
	// if (Math.random() < 0.8) {
	// COMMANDE[] cmds = { COMMANDE.GAUCHE, COMMANDE.DROITE,
	// COMMANDE.HAUT, COMMANDE.BAS };
	// return cmds[(int) (Math.random() * cmds.length)];
	// } else if (Math.random() < 0.8) {
	// return COMMANDE.FRAPPE;
	// } else {
	// COMMANDE[] cmds = { COMMANDE.RAMASSER, COMMANDE.JETER };
	// return cmds[(int) (Math.random() * cmds.length)];
	// }
	// }
}

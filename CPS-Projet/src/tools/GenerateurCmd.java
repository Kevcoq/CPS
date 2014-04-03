package tools;

import enumeration.COMMANDE;

public class GenerateurCmd {
	public static COMMANDE genererCmd() {
		COMMANDE[] cmds = COMMANDE.values();
		return cmds[(int) (Math.random() * cmds.length)];
	}
}

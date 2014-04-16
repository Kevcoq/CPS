package mains.toolsIHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enumeration.COMMANDE;

/**
 * L'action listener
 * 
 * @author Kevin & Quentin
 * 
 */
public class Ecouteur implements ActionListener {
	private COMMANDE cmd;

	/**
	 * renvoie la commande rentre ou null
	 * 
	 * @return cmd ou null
	 */
	public COMMANDE cmd() {
		COMMANDE tmp = cmd;
		cmd = null;
		return tmp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cmd = COMMANDE.valueOf(e.getActionCommand().toUpperCase());
	}
}

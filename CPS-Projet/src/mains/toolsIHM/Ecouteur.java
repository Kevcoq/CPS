package mains.toolsIHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enumeration.COMMANDE;

public class Ecouteur implements ActionListener {
	private COMMANDE cmd;

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

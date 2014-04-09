package mains;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import mains.toolsIHM.Affichage;
import mains.toolsIHM.Ecouteur;
import services.MoteurJeuService;
import enumeration.COMMANDE;

public class IHM {
	private int coef = Mains.coef;
	private JFrame frame;
	private Ecouteur ecoute;

	private MoteurJeuService moteur;

	public IHM(MoteurJeuService moteur) {
		super();
		this.moteur = moteur;
		frame = new JFrame("Fight !!!");
	}

	public void go() throws InterruptedException {
		fenetre();

		Thread.sleep(100);
		// boucle principal
		while (!moteur.estFini()) {
			if (moteur.pasJeuCourant() % 10 == 0)
				System.out.println("Pas jeu courant : "
						+ moteur.pasJeuCourant());

			// automatique
			// moteur.pasJeu(GenerateurCmd.genererCmd(),
			// GenerateurCmd.genererCmd());

			// manuel
			COMMANDE tmp;
			while ((tmp = ecoute.cmd()) == null)
				Thread.sleep(50);
			moteur.pasJeu("Alex", tmp);

			// graphisme
			frame.repaint();
			Thread.sleep(500);

			System.out.println(moteur.combat());
		}
	}

	private void fenetre() {
		// fenetre
		JPanel jp = new Affichage(moteur);
		JPanel pBouton = new JPanel();
		bouton(pBouton);
		frame.setLayout(new BorderLayout());
		frame.add(jp, BorderLayout.CENTER);
		jp.setPreferredSize(new Dimension(moteur.combat().terrain().largeur()
				* coef, moteur.combat().terrain().profondeur() * coef));
		frame.add(pBouton, BorderLayout.BEFORE_FIRST_LINE);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// bouton
	private void bouton(JPanel pBouton) {
		// ecouteur
		ecoute = new Ecouteur();

		JMenuBar jmb = new JMenuBar();
		JMenuItem gauche = new JMenuItem("Gauche"), droite = new JMenuItem(
				"Droite"), haut = new JMenuItem("Haut"), bas = new JMenuItem(
				"Bas"), frapper = new JMenuItem("Frappe"), sauter = new JMenuItem(
				"Sauter"), ramasser = new JMenuItem("Ramasser"), jeter = new JMenuItem(
				"Jeter"), rien = new JMenuItem("Rien");

		// ajout au menu
		jmb.add(rien);
		jmb.add(gauche);
		jmb.add(droite);
		jmb.add(haut);
		jmb.add(bas);
		jmb.add(frapper);
		jmb.add(sauter);
		jmb.add(ramasser);
		jmb.add(jeter);
		
		// bar de menu
		pBouton.add(jmb);

		// listener
		gauche.addActionListener(ecoute);
		droite.addActionListener(ecoute);
		haut.addActionListener(ecoute);
		bas.addActionListener(ecoute);
		frapper.addActionListener(ecoute);
		sauter.addActionListener(ecoute);
		ramasser.addActionListener(ecoute);
		jeter.addActionListener(ecoute);
		rien.addActionListener(ecoute);

		// raccourcis
		gauche.setAccelerator(KeyStroke.getKeyStroke('q'));
		droite.setAccelerator(KeyStroke.getKeyStroke('d'));
		haut.setAccelerator(KeyStroke.getKeyStroke('z'));
		bas.setAccelerator(KeyStroke.getKeyStroke('s'));
		frapper.setAccelerator(KeyStroke.getKeyStroke('f'));
		sauter.setAccelerator(KeyStroke.getKeyStroke(' '));
		ramasser.setAccelerator(KeyStroke.getKeyStroke('r'));
		jeter.setAccelerator(KeyStroke.getKeyStroke('j'));
		rien.setAccelerator(KeyStroke.getKeyStroke('!'));
	}
}

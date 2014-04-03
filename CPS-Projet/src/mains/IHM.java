package mains;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		ecoute = new Ecouteur();
		JButton gauche = new JButton("Gauche"), droite = new JButton("Droite"), haut = new JButton(
				"Haut"), bas = new JButton("Bas"), sauter = new JButton(
				"Sauter"), ramasser = new JButton("Ramasser"), jeter = new JButton(
				"Jeter"), rien = new JButton("Rien");

		pBouton.add(gauche);
		gauche.addActionListener(ecoute);

		pBouton.add(droite);
		droite.addActionListener(ecoute);

		pBouton.add(haut);
		haut.addActionListener(ecoute);

		pBouton.add(bas);
		bas.addActionListener(ecoute);

		pBouton.add(sauter);
		sauter.addActionListener(ecoute);

		pBouton.add(ramasser);
		ramasser.addActionListener(ecoute);

		pBouton.add(jeter);
		jeter.addActionListener(ecoute);

		pBouton.add(rien);
		rien.addActionListener(ecoute);
	}
}

package mains.toolsIHM;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import mains.Mains;
import enumeration.TYPE_Bloc;
import services.GestionCombatService;
import services.MoteurJeuService;
import services.PersonnageService;
import services.TerrainService;

public class Affichage extends JPanel {
	private MoteurJeuService moteur;

	public Affichage(MoteurJeuService moteur) {
		super();
		this.moteur = moteur;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		int coef = Mains.coef;
		// racourcis
		GestionCombatService cbt = moteur.combat();
		TerrainService terrain = cbt.terrain();

		//
		//
		// bloc
		for (int i = 0; i < terrain.largeur(); i++)
			for (int j = 0; j < terrain.profondeur(); j++) {
				if (terrain.getBloc(i, j, 0).typeBloc() == TYPE_Bloc.VIDE)
					g.setColor(Color.GRAY);
				else
					g.setColor(Color.BLACK);
				g.fillRect(i * coef, j * coef, coef, coef);
			}

		//
		//
		//
		//
		// tableau statut personnage
		int[][] colorPerso = new int[terrain.largeur()][terrain.profondeur()];
		// 1 gangster | 2 slick | 3 alex | 4 ryan | 10 gangsterS
		// | 20 Slick + Alex ou/et Ryan | 30 Alex et/ou Ryan + gangster
		// | 40 Alex et Ryan
		for (PersonnageService p : cbt.mPerso().values()) {
			if (p.nom().equals("Slick"))
				ajouter(colorPerso, cbt.position(p.nom()).x(),
						cbt.position(p.nom()).y(), 2);
			else if (p.nom().equals("Alex"))
				ajouter(colorPerso, cbt.position(p.nom()).x(),
						cbt.position(p.nom()).y(), 3);
			else if (p.nom().equals("Ryan"))
				ajouter(colorPerso, cbt.position(p.nom()).x(),
						cbt.position(p.nom()).y(), 4);
			else
				ajouter(colorPerso, cbt.position(p.nom()).x(),
						cbt.position(p.nom()).y(), 1);
		}

		//
		//
		//
		//
		// colorer les personnages
		for (int i = 0; i < colorPerso.length; i++)
			for (int j = 0; j < colorPerso[0].length; j++) {
				if (colorPerso[i][j] == 1)
					g.setColor(Color.YELLOW);
				else if (colorPerso[i][j] == 2)
					g.setColor(Color.RED);
				else if (colorPerso[i][j] == 3 || colorPerso[i][j] == 4)
					g.setColor(Color.GREEN);
				else if (colorPerso[i][j] == 10)
					g.setColor(Color.ORANGE);
				else if (colorPerso[i][j] == 20)
					g.setColor(Color.MAGENTA);
				else if (colorPerso[i][j] == 30)
					g.setColor(Color.PINK);
				else if (colorPerso[i][j] == 40)
					g.setColor(Color.BLUE);
				else
					continue;
				g.fillRect(i * coef, j * coef, coef, coef);
			}

	}

	private static void ajouter(int[][] tab, int x, int y, int nb) {
		// 1 gangster | 2 slick | 3 alex | 4 ryan | 10 gangsterS
		// | 20 Slick + Alex ou/et Ryan | 30 Alex et/ou Ryan + gangster
		// | 40 Alex et Ryan
		switch (nb) {
		case 1:
			switch (tab[x][y]) {
			case 0:
				tab[x][y] = 1;
				break;
			case 1:
				tab[x][y] = 10;
				break;
			}
			break;
		case 2:
			switch (tab[x][y]) {
			case 3:
				tab[x][y] = 20;
				break;
			case 4:
				tab[x][y] = 20;
				break;
			case 30:
				tab[x][y] = 20;
				break;
			case 40:
				tab[x][y] = 20;
				break;
			default:
				tab[x][y] = 2;
			}
			break;
		case 3:
			switch (tab[x][y]) {
			case 1:
				tab[x][y] = 30;
				break;
			case 2:
				tab[x][y] = 20;
				break;
			case 4:
				tab[x][y] = 40;
				break;
			case 10:
				tab[x][y] = 30;
				break;
			default:
				tab[x][y] = 3;
			}

			break;
		case 4:
			switch (tab[x][y]) {
			case 1:
				tab[x][y] = 30;
				break;
			case 2:
				tab[x][y] = 20;
				break;
			case 3:
				tab[x][y] = 40;
				break;
			case 10:
				tab[x][y] = 30;
				break;
			default:
				tab[x][y] = 4;
			}
			break;
		}
	}
}

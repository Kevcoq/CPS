package mains.toolsIHM;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import mains.Mains;
import services.GestionCombatService;
import services.MoteurJeuService;
import services.PersonnageService;
import services.PositionService;
import services.TerrainService;
import enumeration.TYPE_Bloc;
import enumeration.TYPE_Tresor;

/**
 * Gere l'affichage en tps reel
 * 
 * @author Kevin & Quentin
 * 
 */
public class Affichage extends JPanel {
	private MoteurJeuService moteur;
	private int coef = Mains.coef;

	public Affichage(MoteurJeuService moteur) {
		super();
		this.moteur = moteur;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * transforme le tresor en chaine de caractere pour l'affichage
	 * 
	 * @param tresor
	 * @return
	 */
	private String tresorToString(TYPE_Tresor tresor) {
		switch (tresor) {
		case CINQDOLLAR:
			return "5";
		case DIXDOLLAR:
			return "10";
		case CHAINEVELO:
			return "CV";
		case POUBELLEMETALLIQUE:
			return "PM";

		default:
			return null;
		}
	}

	public void paintComponent(Graphics g) {
		// racourcis
		GestionCombatService cbt = moteur.combat();
		TerrainService terrain = cbt.terrain();

		// bloc
		colorier_bloc(g, terrain);

		// personnage
		colorier_personnage(g, cbt, terrain);

		// objet
		colorier_objet(g, terrain);

	}

	/**
	 * ecrit le nom des objets
	 * 
	 * @param g
	 * @param terrain
	 *            le terrain
	 */
	private void colorier_objet(Graphics g, TerrainService terrain) {
		g.setColor(Color.WHITE);
		for (int i = 0; i < terrain.largeur(); i++)
			for (int j = 0; j < terrain.profondeur(); j++)
				if (terrain.getBloc(i, j, 0).aTresor())
					g.drawString(tresorToString(terrain.getBloc(i, j, 0)
							.typeTresor()), i * coef,
							(terrain.profondeur() - j) * coef);
	}

	/**
	 * colorie les personnages
	 * 
	 * @param g
	 * @param cbt
	 *            la gestion du combat
	 * @param terrain
	 *            le terrain
	 */
	private void colorier_personnage(Graphics g, GestionCombatService cbt,
			TerrainService terrain) {
		// tableau statut personnage
		int[][] colorPerso = new int[terrain.largeur()][terrain.profondeur()];
		// 1 gangster | 2 slick | 3 alex | 4 ryan | 10 gangsterS
		// | 20 Slick + Alex ou/et Ryan | 30 Alex et/ou Ryan + gangster
		// | 40 Alex et Ryan
		for (PersonnageService p : cbt.mPerso().values()) {
			if (p.nom().equals("Slick"))
				ajouter(colorPerso, cbt.position(p.nom()), 2);
			else if (p.nom().equals("Alex"))
				ajouter(colorPerso, cbt.position(p.nom()), 3);
			else if (p.nom().equals("Ryan"))
				ajouter(colorPerso, cbt.position(p.nom()), 4);
			else
				ajouter(colorPerso, cbt.position(p.nom()), 1);
		}

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
				g.fillRect(i * coef, (terrain.profondeur() - 1 - j) * coef,
						coef, coef);
			}
	}

	/**
	 * colorie les blocs
	 * 
	 * @param g
	 * @param terrain
	 *            le terrain
	 */
	private void colorier_bloc(Graphics g, TerrainService terrain) {
		for (int i = 0; i < terrain.largeur(); i++)
			for (int j = 0; j < terrain.profondeur(); j++) {
				if (terrain.getBloc(i, j, 0).typeBloc() == TYPE_Bloc.VIDE)
					g.setColor(Color.GRAY);
				else
					g.setColor(Color.BLACK);
				g.fillRect(i * coef, (terrain.profondeur() - 1 - j) * coef,
						coef, coef);
			}
	}

	/**
	 * gere la couleur en fction des personnages sur le bloc
	 * 
	 * @param tab
	 *            le tableau pour gerer les couleurs
	 * @param pos
	 *            la position
	 * @param nb
	 *            l'entier correspondant a une situation
	 */
	private static void ajouter(int[][] tab, PositionService pos, int nb) {
		// 1 gangster | 2 slick | 3 alex | 4 ryan | 10 gangsterS
		// | 20 Slick + Alex ou/et Ryan | 30 Alex et/ou Ryan + gangster
		// | 40 Alex et Ryan
		switch (nb) {
		case 1:
			switch (tab[pos.x()][pos.y()]) {
			case 0:
				tab[pos.x()][pos.y()] = 1;
				break;
			case 1:
				tab[pos.x()][pos.y()] = 10;
				break;
			}
			break;
		case 2:
			switch (tab[pos.x()][pos.y()]) {
			case 3:
				tab[pos.x()][pos.y()] = 20;
				break;
			case 4:
				tab[pos.x()][pos.y()] = 20;
				break;
			case 30:
				tab[pos.x()][pos.y()] = 20;
				break;
			case 40:
				tab[pos.x()][pos.y()] = 20;
				break;
			default:
				tab[pos.x()][pos.y()] = 2;
			}
			break;
		case 3:
			switch (tab[pos.x()][pos.y()]) {
			case 1:
				tab[pos.x()][pos.y()] = 30;
				break;
			case 2:
				tab[pos.x()][pos.y()] = 20;
				break;
			case 4:
				tab[pos.x()][pos.y()] = 40;
				break;
			case 10:
				tab[pos.x()][pos.y()] = 30;
				break;
			default:
				tab[pos.x()][pos.y()] = 3;
			}

			break;
		case 4:
			switch (tab[pos.x()][pos.y()]) {
			case 1:
				tab[pos.x()][pos.y()] = 30;
				break;
			case 2:
				tab[pos.x()][pos.y()] = 20;
				break;
			case 3:
				tab[pos.x()][pos.y()] = 40;
				break;
			case 10:
				tab[pos.x()][pos.y()] = 30;
				break;
			default:
				tab[pos.x()][pos.y()] = 4;
			}
			break;
		}
	}
}

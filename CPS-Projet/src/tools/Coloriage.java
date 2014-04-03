package tools;

import java.awt.Color;
import java.awt.image.BufferedImage;

import mains.Mains;
import services.GestionCombatService;
import services.MoteurJeuService;
import services.PersonnageService;
import services.TerrainService;
import enumeration.TYPE_Bloc;

public class Coloriage {
	private static int coef = Mains.coef;

	public static BufferedImage image(MoteurJeuService moteur) {
		// racourcis
		GestionCombatService cbt = moteur.combat();
		TerrainService terrain = cbt.terrain();

		//
		// img
		BufferedImage img = new BufferedImage(terrain.largeur() * coef,
				terrain.profondeur() * coef, BufferedImage.TYPE_3BYTE_BGR);

		//
		//
		// bloc
		for (int i = 0; i < terrain.largeur(); i++)
			for (int j = 0; j < terrain.profondeur(); j++)
				if (terrain.getBloc(i, j, 0).typeBloc() == TYPE_Bloc.VIDE)
					colorier(img, i, j, Color.GRAY);
				else
					colorier(img, i, j, Color.BLACK);

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
			for (int j = 0; j < colorPerso[0].length; j++)
				if (colorPerso[i][j] == 1)
					colorier(img, i, j, Color.YELLOW);
				else if (colorPerso[i][j] == 2)
					colorier(img, i, j, Color.RED);
				else if (colorPerso[i][j] == 3 || colorPerso[i][j] == 4)
					colorier(img, i, j, Color.GREEN);
				else if (colorPerso[i][j] == 10)
					colorier(img, i, j, Color.ORANGE);
				else if (colorPerso[i][j] == 20)
					colorier(img, i, j, Color.MAGENTA);
				else if (colorPerso[i][j] == 30)
					colorier(img, i, j, Color.PINK);
				else if (colorPerso[i][j] == 40)
					colorier(img, i, j, Color.BLUE);
		
		return img;
	}

	private static void colorier(BufferedImage img, int x, int y, Color c) {
		for (int i = x * coef; i < x * coef + coef; i++)
			for (int j = y * coef; j < y * coef + coef; j++)
				img.setRGB(i, j, c.getRGB());

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

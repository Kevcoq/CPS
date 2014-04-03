package mains;

import implem.MoteurJeu;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import services.MoteurJeuService;
import tools.Coloriage;

public class Mains {
	public static int coef = 20;

	public static void main(String[] args) throws IOException,
			InterruptedException {

		// moteur
		MoteurJeuService moteur = new MoteurJeu();
		moteur.init(20, 4, 10, 100);

		// sauvegarde img statique
		File outputfile = new File("saved.png");
		ImageIO.write(Coloriage.image(moteur), "png", outputfile);

		IHM ihm = new IHM(moteur);
		ihm.go();

		// sv img statique de fin
		File outputfileFin = new File("savedFin.png");
		ImageIO.write(Coloriage.image(moteur), "png", outputfileFin);

		// resultat
		System.out.println("Resultat : " + moteur.resultatFinal());
	}

}

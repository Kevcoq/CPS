package implem;

import services.PersonnageService;

public class Personnage implements PersonnageService {

	private String nom;
	private int largeur;
	private int hauteur;
	private int force;
	private int pdv;

	public Personnage() {
	}

	@Override
	public String nom() {
		return nom;
	}

	@Override
	public int largeur() {
		return largeur;
	}

	@Override
	public int hauteur() {
		return hauteur;
	}

	@Override
	public int force() {
		return force;
	}

	@Override
	public int pointsDeVie() {
		return pdv;
	}

	@Override
	public boolean estVaincu() {
		return pdv == 0;
	}

	@Override
	public void init(String nom, int largeur, int hauteur, int force, int pdv) {
		this.nom = nom;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.force = force;
		this.pdv = pdv;
	}

	@Override
	public void retrait(int s) {
		pdv -= s;
	}

}

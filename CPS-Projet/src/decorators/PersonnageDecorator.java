package decorators;

import services.PersonnageService;

public abstract class PersonnageDecorator implements PersonnageService {
	private PersonnageService personnage;

	public PersonnageDecorator(PersonnageService personnage) {
		super();
		this.personnage = personnage;
	}

	@Override
	public String nom() {
		return personnage.nom();
	}

	@Override
	public int largeur() {
		return personnage.largeur();
	}

	@Override
	public int hauteur() {
		return personnage.hauteur();
	}

	@Override
	public int force() {
		return personnage.force();
	}

	@Override
	public int pointsDeVie() {
		return personnage.pointsDeVie();
	}

	@Override
	public boolean estVaincu() {
		return personnage.estVaincu();
	}

	@Override
	public void init(String nom, int largeur, int hauteur, int force, int pdv) {
		personnage.init(nom, largeur, hauteur, force, pdv);
	}

	@Override
	public void retrait(int s) {
		personnage.retrait(s);
	}

}

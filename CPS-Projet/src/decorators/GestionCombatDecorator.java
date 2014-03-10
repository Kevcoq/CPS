package decorators;

import enumeration.COMMANDE;
import services.GestionCombatService;
import services.PersonnageService;

public abstract class GestionCombatDecorator implements GestionCombatService {
	private GestionCombatService gestion;

	public GestionCombatDecorator(GestionCombatService gestion) {
		super();
		this.gestion = gestion;
	}

	@Override
	public int largeur() {
		return gestion.largeur();
	}

	@Override
	public int hauteur() {
		return gestion.hauteur();
	}

	@Override
	public PersonnageService alex() {
		return gestion.alex();
	}

	@Override
	public PersonnageService ryan() {
		return gestion.ryan();
	}

	@Override
	public boolean estFrappe(String nom) {
		return gestion.estFrappe(nom);
	}

	@Override
	public boolean estGele(String nom) {
		return gestion.estGele(nom);
	}

	@Override
	public int[] position(String nom) {
		return gestion.position(nom);
	}

	@Override
	public boolean collision(String nom) {
		return gestion.collision(nom);
	}

	@Override
	public void init(int largeur, int hauteur) {
		gestion.init(largeur, hauteur);
	}

	@Override
	public void gerer(String nom, COMMANDE cmd) {
		gestion.gerer(nom, cmd);
	}

}

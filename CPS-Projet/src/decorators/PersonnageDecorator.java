package decorators;

import services.ChoseService;
import services.PersonnageService;

/**
 * Decorator de Personnage
 * 
 * @author Kevin & Quentin
 * 
 */
public abstract class PersonnageDecorator  implements PersonnageService {
	private PersonnageService personnage;

	public PersonnageDecorator(PersonnageService personnage) {
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
	public void init(String nom, int largeur, int hauteur, int profondeur,
			int force, int pdv) {
		personnage.init(nom, largeur, hauteur, profondeur, force, pdv);
	}

	@Override
	public void retraitPdv(int s) {
		personnage.retraitPdv(s);
	}

	@Override
	public int profondeur() {
		return personnage.profondeur();
	}

	@Override
	public int sommeArgent() {
		return personnage.sommeArgent();
	}

	@Override
	public boolean estEquipe() {
		return personnage.estEquipe();
	}

	@Override
	public ChoseService laChoseEquipee() {
		return personnage.laChoseEquipee();
	}

	@Override
	public void depotPdv(int s) {
		personnage.depotPdv(s);
	}

	@Override
	public void retraitArgent(int s) {
		personnage.retraitArgent(s);
	}

	@Override
	public void depotArgent(int s) {
		personnage.depotArgent(s);
	}

	@Override
	public void ramasser(ChoseService o) {
		personnage.ramasser(o);
	}

	@Override
	public void jeter() {
		personnage.jeter();
	}

	/**
	 * @return
	 * @see services.ChoseService#bonus()
	 */
	public int bonus() {
		return personnage.bonus();
	}

	/**
	 * @return
	 * @see services.ChoseService#estPorte()
	 */
	public boolean estPorte() {
		return personnage.estPorte();
	}

	/**
	 * @param b
	 * @see services.ChoseService#init(int)
	 */
	public void init(int b) {
		personnage.init(b);
	}

	/**
	 * 
	 * @see services.ChoseService#estRamasse()
	 */
	public void estRamasse() {
		personnage.estRamasse();
	}

	/**
	 * 
	 * @see services.ChoseService#estJete()
	 */
	public void estJete() {
		personnage.estJete();
	}
	
	

}

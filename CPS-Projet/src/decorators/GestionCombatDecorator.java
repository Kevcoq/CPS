package decorators;

import java.util.List;
import java.util.Map;

import services.GestionCombatService;
import services.PersonnageService;
import services.PositionService;
import services.TerrainService;
import enumeration.COMMANDE;

public abstract class GestionCombatDecorator implements GestionCombatService {
	private GestionCombatService cbt;

	/**
	 * @param nom1
	 * @param nom2
	 * @return
	 * @see services.GestionCombatService#collisionGauche(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean collisionGauche(String nom1, String nom2) {
		return cbt.collisionGauche(nom1, nom2);
	}

	public GestionCombatDecorator(GestionCombatService cbt) {
		super();
		this.cbt = cbt;
	}

	/**
	 * @return
	 * @see services.GestionCombatService#terrain()
	 */
	public TerrainService terrain() {
		return cbt.terrain();
	}

	/**
	 * @return
	 * @see services.GestionCombatService#mPerso()
	 */
	public Map<String, PersonnageService> mPerso() {
		return cbt.mPerso();
	}

	/**
	 * @param nom
	 * @return
	 * @see services.GestionCombatService#estFrappe(java.lang.String)
	 */
	public boolean estFrappe(String nom) {
		return cbt.estFrappe(nom);
	}

	/**
	 * @param nom
	 * @return
	 * @see services.GestionCombatService#estGele(java.lang.String)
	 */
	public boolean estGele(String nom) {
		return cbt.estGele(nom);
	}

	/**
	 * @param nom
	 * @return
	 * @see services.GestionCombatService#cptGele(java.lang.String)
	 */
	public int cptGele(String nom) {
		return cbt.cptGele(nom);
	}

	/**
	 * @param nom
	 * @return
	 * @see services.GestionCombatService#position(java.lang.String)
	 */
	public PositionService position(String nom) {
		return cbt.position(nom);
	}

	/**
	 * @param nom
	 * @return
	 * @see services.GestionCombatService#collision(java.lang.String)
	 */
	public List<PersonnageService> collision(String nom) {
		return cbt.collision(nom);
	}

	/**
	 * @param largeur
	 * @param hauteur
	 * @param profondeur
	 * @see services.GestionCombatService#init(int, int, int)
	 */
	public void init(int largeur, int hauteur, int profondeur) {
		cbt.init(largeur, hauteur, profondeur);
	}

	/**
	 * @param cmd
	 * @see services.GestionCombatService#gerer(java.util.Map)
	 */
	public void gerer(Map<String, COMMANDE> cmd) {
		cbt.gerer(cmd);
	}

}

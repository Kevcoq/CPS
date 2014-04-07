package decorators;

import services.GestionCombatService;
import services.MoteurJeuService;
import enumeration.COMMANDE;
import enumeration.RESULTAT;

public abstract class MoteurJeuDecorator implements MoteurJeuService {
	private MoteurJeuService moteur;

	public MoteurJeuDecorator(MoteurJeuService moteur) {
		super();
		this.moteur = moteur;
	}

	/**
	 * @return
	 * @see services.MoteurJeuService#maxPasJeu()
	 */
	public int maxPasJeu() {
		return moteur.maxPasJeu();
	}

	/**
	 * @return
	 * @see services.MoteurJeuService#pasJeuCourant()
	 */
	public int pasJeuCourant() {
		return moteur.pasJeuCourant();
	}

	/**
	 * @return
	 * @see services.MoteurJeuService#estFini()
	 */
	public boolean estFini() {
		return moteur.estFini();
	}

	/**
	 * @return
	 * @see services.MoteurJeuService#resultatFinal()
	 */
	public RESULTAT resultatFinal() {
		return moteur.resultatFinal();
	}

	/**
	 * @return
	 * @see services.MoteurJeuService#combat()
	 */
	public GestionCombatService combat() {
		return moteur.combat();
	}

	/**
	 * @param largeur
	 * @param hauteur
	 * @param profondeur
	 * @param maxPas
	 * @see services.MoteurJeuService#init(int, int, int, int)
	 */
	public void init(int largeur, int hauteur, int profondeur, int maxPas) {
		moteur.init(largeur, hauteur, profondeur, maxPas);
	}

	/**
	 * @param cmdAlex
	 * @param cmdRyan
	 * @see services.MoteurJeuService#pasJeu(enumeration.COMMANDE,
	 *      enumeration.COMMANDE)
	 */
	public void pasJeu(COMMANDE cmdAlex, COMMANDE cmdRyan) {
		moteur.pasJeu(cmdAlex, cmdRyan);
	}

	/**
	 * @param nom
	 * @param cmd
	 * @see services.MoteurJeuService#pasJeu(String nom, enumeration.COMMANDE)
	 */
	public void pasJeu(String nom, COMMANDE cmd) {
		moteur.pasJeu(nom, cmd);
	}

}

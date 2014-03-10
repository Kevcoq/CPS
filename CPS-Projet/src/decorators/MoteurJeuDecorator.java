package decorators;

import enumeration.COMMANDE;
import enumeration.RESULTAT;
import services.GestionCombatService;
import services.MoteurJeuService;

public abstract class MoteurJeuDecorator implements MoteurJeuService {
	private MoteurJeuService moteurJeu;

	public MoteurJeuDecorator(MoteurJeuService moteurJeu) {
		super();
		this.moteurJeu = moteurJeu;
	}

	@Override
	public int maxPasJeu() {
		return moteurJeu.maxPasJeu();
	}

	@Override
	public int pasJeuCourant() {
		return moteurJeu.pasJeuCourant();
	}

	@Override
	public boolean estFini() {
		return moteurJeu.estFini();
	}

	@Override
	public RESULTAT resultatFinal() {
		return moteurJeu.resultatFinal();
	}

	@Override
	public GestionCombatService combat() {
		return moteurJeu.combat();
	}

	@Override
	public void init(int largeur, int hauteur, int maxPas) {
		moteurJeu.init(largeur, hauteur, maxPas);
	}

	@Override
	public void pasJeu(COMMANDE cmd) {
		moteurJeu.pasJeu(cmd);
	}

}

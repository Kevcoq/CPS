package implem;

import java.util.HashMap;
import java.util.Map;

import services.GestionCombatService;
import services.MoteurJeuService;
import enumeration.COMMANDE;
import enumeration.RESULTAT;

public class MoteurJeu implements MoteurJeuService {
	private int maxPasJeu = 0, pasJeuCourant = 0;
	private boolean estFini = false;
	private RESULTAT resultat;
	private GestionCombatService cbt;

	@Override
	public int maxPasJeu() {
		return maxPasJeu;
	}

	@Override
	public int pasJeuCourant() {
		return pasJeuCourant;
	}

	@Override
	public boolean estFini() {
		return estFini;
	}

	@Override
	public RESULTAT resultatFinal() {
		return resultat;
	}

	@Override
	public GestionCombatService combat() {
		return cbt;
	}

	@Override
	public void init(int largeur, int hauteur, int profondeur, int maxPas) {
		cbt.init(largeur, hauteur, profondeur);
		maxPasJeu = maxPas;
		pasJeuCourant = 0;
	}

	@Override
	public void pasJeu(COMMANDE cmdAlex, COMMANDE cmdRyan) {
		pasJeuCourant++;
		Map<String, COMMANDE> mCmd = new HashMap<String, COMMANDE>();
		mCmd.put("Alex", cmdAlex);
		mCmd.put("Ryan", cmdRyan);
		cbt.gerer(mCmd);
	}

	@Override
	public void pasJeu(String nom, COMMANDE cmd) {
		pasJeuCourant++;
		Map<String, COMMANDE> mCmd = new HashMap<String, COMMANDE>();
		mCmd.put(nom, cmd);
		cbt.gerer(mCmd);
	}

}

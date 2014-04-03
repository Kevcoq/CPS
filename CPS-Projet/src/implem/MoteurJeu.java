package implem;

import java.util.HashMap;
import java.util.Map;

import services.GestionCombatService;
import services.MoteurJeuService;
import enumeration.COMMANDE;
import enumeration.RESULTAT;

public class MoteurJeu implements MoteurJeuService {
	static class Rand {
		private static int n = 0;

		public static String name() {
			return "Gangster" + n++;
		}
	}

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
		cbt = new GestionCombat();
		cbt.init(largeur, hauteur, profondeur);
		maxPasJeu = maxPas;
		pasJeuCourant = 0;
	}

	@Override
	public void pasJeu(COMMANDE cmdAlex, COMMANDE cmdRyan) {
		pasJeuCourant++;
		Map<String, COMMANDE> mCmd = generationCmds();

		mCmd.put("Alex", cmdAlex);
		mCmd.put("Ryan", cmdRyan);

		cbt.gerer(mCmd);
	}

	@Override
	public void pasJeu(String nom, COMMANDE cmd) {
		pasJeuCourant++;
		Map<String, COMMANDE> mCmd = generationCmds();

		mCmd.put(nom, cmd);

		cbt.gerer(mCmd);
	}

	private Map<String, COMMANDE> generationCmds() {
		Map<String, COMMANDE> cmd = new HashMap<>();
		for (String nom : cbt.mPerso().keySet())
			cmd.put(nom, genererCmd());

		if (Math.random() < 0.15)
			cmd.put(Rand.name(), genererCmd());

		return cmd;
	}

	private COMMANDE genererCmd() {
		COMMANDE[] cmds = COMMANDE.values();
		return cmds[(int) (Math.random() * cmds.length)];
	}

}

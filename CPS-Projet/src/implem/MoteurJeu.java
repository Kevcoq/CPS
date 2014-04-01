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

		generationCmds(mCmd);

		cbt.gerer(mCmd);
	}

	@Override
	public void pasJeu(String nom, COMMANDE cmd) {
		pasJeuCourant++;
		Map<String, COMMANDE> mCmd = new HashMap<String, COMMANDE>();
		mCmd.put(nom, cmd);

		generationCmds(mCmd);

		cbt.gerer(mCmd);
	}

	private void generationCmds(Map<String, COMMANDE> cmd) {
		String[] noms = (String[]) cbt.mPerso().keySet().toArray();
		for (int i = 0; i < noms.length; i++)
			cmd.put(noms[i], genererCmd());

		if (Math.random() < 0.15)
			cmd.put(Rand.name(), genererCmd());
	}

	private COMMANDE genererCmd() {
		COMMANDE[] cmds = COMMANDE.values();
		return cmds[(int) (Math.random() * cmds.length)];
	}

}

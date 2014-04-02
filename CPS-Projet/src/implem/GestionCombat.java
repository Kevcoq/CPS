package implem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import services.ChoseService;
import services.GangsterService;
import services.GestionCombatService;
import services.PersonnageService;
import services.TerrainService;
import enumeration.COMMANDE;

public class GestionCombat implements GestionCombatService {

	class Position {
		private int x, y, z;

		private Position(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}

		private int[] toTbl() {
			int[] tmp = { x, y, z };
			return tmp;
		}
	}

	class FG {
		private boolean estFrappe;
		private boolean estGele;
		private int nbGele;

		private FG() {
			super();
			this.estFrappe = false;
			this.estGele = false;
			this.nbGele = 0;
		}
	}

	private TerrainService terrain;
	private Map<String, PersonnageService> mPerso;
	private Map<String, FG> mFG;
	private Map<String, Position> mPos;

	@Override
	public TerrainService terrain() {
		return terrain;
	}

	@Override
	public Map<String, PersonnageService> mPerso() {
		return mPerso;
	}

	@Override
	public boolean estFrappe(String nom) {
		return mFG.get(nom).estFrappe;
	}

	@Override
	public boolean estGele(String nom) {
		return mFG.get(nom).estGele;
	}

	@Override
	public int cptGele(String nom) {
		return mFG.get(nom).nbGele;
	}

	@Override
	public int[] position(String nom) {
		return mPos.get(nom).toTbl();
	}

	@Override
	public boolean collisionGauche(String nom1, String nom2) {
		// TODO
		PersonnageService p1 = mPerso.get(nom1), p2 = mPerso.get(nom2);
		return false;
	}

	@Override
	public List<PersonnageService> collision(String nom) {
		Collection<PersonnageService> persos = mPerso.values();
		persos.remove(mPerso.get(nom));
		List<PersonnageService> lPerso = new ArrayList<PersonnageService>();
		for (PersonnageService p : persos) {
			if (collisionGauche(nom, p.nom()) || collisionGauche(p.nom(), nom))
				lPerso.add(p);
		}
		return lPerso;
	}

	@Override
	public void init(int largeur, int hauteur, int profondeur) {
		// terrain
		terrain = new Terrain();
		terrain.init(largeur, hauteur, profondeur);

		// personnage + position
		mPerso = new HashMap<String, PersonnageService>();
		mPos = new HashMap<String, GestionCombat.Position>();

		PersonnageService alex = new Personnage();
		alex.init("Alex", 20, 51, 10, 100, 1664);
		mPerso.put("Alex", alex);
		mPos.put("Alex", new Position(10 + alex.largeur() / 2 + 1, profondeur
				/ 2 - 10 - alex.profondeur() / 2, 0));

		PersonnageService ryan = new Personnage();
		ryan.init("Ryan", 25, 60, 12, 200, 1664);
		mPerso.put("Ryan", ryan);
		mPos.put("Ryan", new Position(10 + ryan.largeur() / 2 + 1, profondeur
				/ 2 + 10 - ryan.profondeur() / 2, 0));

		GangsterService slick = new Gangster();
		slick.init("slick", 35, 80, 20, 250, 2000);
		mPerso.put("Slick", slick);
		mPos.put("Slick", new Position(largeur - 10 - slick.largeur() / 2 - 1,
				profondeur / 2 + slick.profondeur() / 2, 0));

		Random r = new Random();
		for (int i = 0; i < 3; i++) {
			GangsterService g = new Gangster();
			String name = MoteurJeu.Rand.name();
			g.init(name, 18, 45, 9, 50, 500);
			mPerso.put(name, g);
			mPos.put(name,
					new Position(r.nextInt(largeur), r.nextInt(profondeur), 0));
		}

		// nom
		String[] noms = (String[]) mPerso.keySet().toArray();

		// frappe - gele
		mFG = new HashMap<String, GestionCombat.FG>();
		for (int i = 0; i < noms.length; i++)
			mFG.put(noms[i], new FG());

	}

	@Override
	public void gerer(Map<String, COMMANDE> cmd) {
		// TODO Auto-generated method stub
		Collection<PersonnageService> persos = mPerso.values();
		for (PersonnageService p : persos) {
			if (!p.estPorte()) {
				if (mFG.get(p.nom()).estFrappe) {
					int cpt = 0;
					for (PersonnageService tmp : collision(p.nom()))
						if (cmd.get(tmp.nom()) == COMMANDE.FRAPPE)
							cpt += tmp.force();
					p.retraitPdv(cpt);
					mFG.get(p).estGele = true;
					mFG.get(p).estFrappe = false;
					// TODO cpt gele
					// TODO deplacement
				} else if (mFG.get(p.nom()).estGele) {
					if (--mFG.get(p.nom()).nbGele <= 0)
						mFG.get(p.nom()).estGele = false;
				} else {
					switch (cmd.get(p.nom())) {
					case GAUCHE:
						mPos.get(p.nom()).x = Math.max(0,
								mPos.get(p.nom()).x - 10);
						mPos.get(p.nom()).z = 0;
						break;

					case DROITE:
						mPos.get(p.nom()).x = Math.max(terrain.largeur(),
								mPos.get(p.nom()).x + 10);
						mPos.get(p.nom()).z = 0;
						break;
					case HAUT:
						mPos.get(p.nom()).y = Math.max(terrain.profondeur(),
								mPos.get(p.nom()).y + 10);
						mPos.get(p.nom()).z = 0;
						break;
					case BAS:
						mPos.get(p.nom()).y = Math.max(0,
								mPos.get(p.nom()).y - 10);
						mPos.get(p.nom()).z = 0;
						break;

					case SAUTER:
						mPos.get(p.nom()).z = 100;
						break;

					case FRAPPE:
						for (PersonnageService tmp : collision(p.nom()))
							mFG.get(tmp.nom()).estFrappe = true;
						mFG.get(p.nom()).estGele = true;
						mPos.get(p.nom()).z = 0;
						break;

					case JETER:
						ChoseService chose = p.laChoseEquipee();
						p.jeter();
						chose.estJete();
						// TODO gestion des degats
						if (chose instanceof PersonnageService) {
							// TODO perte de pdv
						}
						mPos.get(p.nom()).z = 0;
						break;

					case RAMASSER:
						// TODO
						break;

					default:
						mPos.get(p.nom()).z = 0;
						break;
					}
				}
			}
		}
	}
}

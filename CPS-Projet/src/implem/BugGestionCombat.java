package implem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import services.BlocService;
import services.ChoseService;
import services.GangsterService;
import services.GestionCombatService;
import services.ObjetEquipableService;
import services.PersonnageService;
import services.PositionService;
import services.TerrainService;
import enumeration.COMMANDE;
import enumeration.TYPE_Bloc;
import enumeration.TYPE_Tresor;

public class BugGestionCombat implements GestionCombatService {
	private Map<String, Integer> bonusObjet;

	class FG {
		private boolean estGele;
		private int nbGele;

		private FG() {
			super();
			this.estGele = false;
			this.nbGele = 0;
		}
	}

	private TerrainService terrain;
	private Map<String, PersonnageService> mPerso;
	private Map<String, FG> mFG;
	private Map<String, PositionService> mPos;

	@Override
	public TerrainService terrain() {
		return terrain;
	}

	@Override
	public Map<String, PersonnageService> mPerso() {
		return mPerso;
	}

	@Override
	public boolean estGele(String nom) {
		if (mFG.containsKey(nom))
			return mFG.get(nom).estGele;
		throw new Error("nom n'existe pas");
	}

	@Override
	public int cptGele(String nom) {
		if (mFG.containsKey(nom))
			return mFG.get(nom).nbGele;
		throw new Error("nom n'existe pas");
	}

	@Override
	public PositionService position(String nom) {
		if (mFG.containsKey(nom)) {
			PositionService pos = mPos.get(nom);
			if (pos.x() >= terrain.largeur())
				throw new ArrayIndexOutOfBoundsException("x = " + pos.x()
						+ " > " + terrain.largeur());
			if (pos.y() >= terrain.profondeur())
				throw new ArrayIndexOutOfBoundsException("y = " + pos.y()
						+ " > " + terrain.profondeur());
			return pos;
		}
		throw new Error("nom n'existe pas");
	}

	@Override
	public boolean collisionGauche(String nom1, String nom2) {
		if (mPos.containsKey(nom1) && mPos.containsKey(nom2)) {
			PositionService pos1 = mPos.get(nom1), pos2 = mPos.get(nom2);
			return pos1.equals(pos2)
					|| pos1.equals(pos2.x() - 1, pos2.y(), pos2.z());
		}
		throw new Error("nom n'existe pas");

	}

	@Override
	public List<PersonnageService> collision(String nom) {
		if (mPerso.containsKey(nom)) {
			List<PersonnageService> lPerso = new ArrayList<PersonnageService>();
			for (String name : mPerso.keySet()) {
				if (!nom.equals(name) && collisionGauche(nom, name)
						|| collisionGauche(name, nom))
					lPerso.add(mPerso.get(name));
			}
			lPerso.remove(mPerso.get(nom));
			return lPerso;
		}
		throw new Error("nom n'existe pas");

	}

	@Override
	public void init(int largeur, int hauteur, int profondeur) {
		// gestion bonus objet
		bonusObjet = new HashMap<String, Integer>();
		bonusObjet.put("CHAINEVELO", 100);
		bonusObjet.put("POUBELLEMETALLIQUE", 500);

		// terrain
		terrain = new Terrain();
		terrain.init(largeur, hauteur, profondeur);

		// personnage + position
		mPerso = new HashMap<String, PersonnageService>();
		mPos = new HashMap<String, PositionService>();

		PersonnageService alex = new Personnage();
		alex.init("Alex", 20, 51, 10, 100, 1664);
		mPerso.put("Alex", alex);
		PositionService posAlex = new Position();
		posAlex.init(0, terrain.profondeur() / 2 + 1, 0, false);
		terrain.getBloc(0, terrain.profondeur() / 2 + 1, 0)
				.init(TYPE_Bloc.VIDE);
		mPos.put("Alex", posAlex);

		PersonnageService ryan = new Personnage();
		ryan.init("Ryan", 25, 60, 12, 200, 1664);
		mPerso.put("Ryan", ryan);
		PositionService posRyan = new Position();
		posRyan.init(0, terrain.profondeur() / 2 - 1, 0, false);
		terrain.getBloc(0, terrain.profondeur() / 2 - 1, 0)
				.init(TYPE_Bloc.VIDE);
		mPos.put("Ryan", posRyan);

		GangsterService slick = new Gangster();
		slick.init("Slick", 35, 80, 20, 250, 2000);
		mPerso.put("Slick", slick);
		PositionService posSlick = new Position();
		posSlick.init(terrain.largeur() - 1, terrain.profondeur() / 2, 0, true);
		terrain.getBloc(terrain.largeur() - 1, terrain.profondeur() / 2, 0)
				.init(TYPE_Bloc.VIDE);
		mPos.put("Slick", posSlick);

		Random r = new Random();
		for (int i = 0; i < 3; i++) {
			GangsterService g = new Gangster();
			String name = MoteurJeu.Rand.name();
			g.init(name, 18, 45, 9, 50, 500);
			mPerso.put(name, g);
			PositionService posTmp = new Position();
			posTmp.init(r.nextInt(largeur), r.nextInt(profondeur), 0, true);
			mPos.put(name, posTmp);
		}

		// frappe - gele
		mFG = new HashMap<String, BugGestionCombat.FG>();
		for (String nom : mPerso.keySet())
			mFG.put(nom, new FG());

	}

	@Override
	public void gerer(Map<String, COMMANDE> cmd) {
		// list des persos
		Collection<PersonnageService> persos = mPerso.values();
		for (PersonnageService p : persos) {

			PositionService pos = mPos.get(p.nom());
			// si !estPorte
			if (!p.estPorte()) {
				// si gele
				if (mFG.get(p.nom()).estGele) {
					if (--mFG.get(p.nom()).nbGele <= 0)
						mFG.get(p.nom()).estGele = false;
				}
				// sinon
				else {
					switch (cmd.get(p.nom())) {
					case GAUCHE:
						deplacement(COMMANDE.DROITE, pos);
						break;
					case DROITE:
						deplacement(COMMANDE.HAUT, pos);
						break;
					case HAUT:
						deplacement(COMMANDE.GAUCHE, pos);
						break;
					case BAS:
						deplacement(COMMANDE.BAS, pos);
						break;
					case SAUTER:
						deplacement(COMMANDE.RIEN, pos);
						break;

					case FRAPPE:
						// frappe
						frapper(p);

						mFG.get(p.nom()).estGele = false;
						pos.setZ(0);
						break;

					case JETER:
						if (p.estEquipe()) {
							ChoseService chose = p.laChoseEquipee();
							p.jeter();
							// chose.estJete();

							gestionDegatJeter(p, chose);
						}

						mFG.get(p.nom()).estGele = true;
						pos.setZ(0);
						break;

					case RAMASSER:
						ramasser(p, pos);

						pos.setZ(0);
						break;

					default:
						pos.setZ(0);
						break;
					}
				}
				if (p.estEquipe()
						&& p.laChoseEquipee() instanceof PersonnageService)
					mPos.get(((PersonnageService) p.laChoseEquipee()).nom())
							.set(pos.x(),
									pos.y(),
									Math.min(pos.z() + 1, terrain.hauteur() - 1));
			}

			// on a traite sa cmd
			cmd.remove(p.nom());
		}
		// surveille les morts
		checkMort(cmd);

		// si il reste des cmd, creation d'un gangster
		if (!cmd.isEmpty()) {
			creationGangster(cmd);
		}

	}

	private void creationGangster(Map<String, COMMANDE> cmd) {
		Random r = new Random();
		for (String s : cmd.keySet()) {
			// on recupere sa futur position
			int x = r.nextInt(terrain.largeur()), y = r.nextInt(terrain
					.profondeur());
			// si c'est un mur, dommage il est mort
			if (terrain.getBloc(x, y, 0).typeBloc() == TYPE_Bloc.VIDE) {
				// sinon on l'initialise
				GangsterService g = new Gangster();
				g.init(s, 18, 45, 9, 50, 500);
				mPerso.put(s, g);
				PositionService posTmp = new Position();
				posTmp.init(r.nextInt(terrain.largeur()),
						r.nextInt(terrain.profondeur()), 0, true);
				mPos.put(s, posTmp);
				mFG.put(s, new FG());
			}
		}
	}

	private void ramasser(PersonnageService p, PositionService pos) {
		// le bloc
		BlocService b = terrain.getBloc(pos.x(), pos.y(), pos.z());

		// si il y a un tresor
		if (b.aTresor()) {
			TYPE_Tresor tTresor = b.typeTresor();
			switch (tTresor) {
			// argent
			case DIXDOLLAR:
				p.depotArgent(10);
				break;
			case CINQDOLLAR:
				p.depotArgent(5);
				break;

			default:
				// objet equipable
				ObjetEquipableService obj = new ObjetEquipable();
				// bonus
				Integer bonus;
				if ((bonus = bonusObjet.get(b.typeTresor().name())) != null)
					obj.init(b.typeTresor().name(), bonus);
				else
					obj.init(b.typeTresor().name(),
							(int) (Math.random() * 100) + 50);
				obj.estRamasse();
				p.ramasser(obj);
				break;
			}

			// on fini par le ramasser
			// b.ramasserTresor();
		}
		// si il n'y a pas d'objet, on ramasse peut etre un personnage
		else {
			for (String s : mPerso.keySet())
				if (pos.equals(mPos.get(s)) && s != p.nom()) {
					PersonnageService tmp = mPerso.get(s);
					tmp.estRamasse();
					mPos.get(tmp.nom()).setZ(1);
					p.ramasser(tmp);
					break;
				}
		}
	}

	private void frapper(PersonnageService p) {
		for (PersonnageService tmp : collision(p.nom())) {
			// on retire les pdv + gele
			if (p.estEquipe())
				tmp.retraitPdv(p.force() + p.laChoseEquipee().bonus());
			else
				tmp.retraitPdv(p.force());
			mFG.get(tmp.nom()).estGele = true;
			// cpt gele
			mFG.get(tmp.nom()).nbGele = 1;

			// deplacement
			PositionService posTmp = mPos.get(tmp.nom());
			for (int i = 0; i < 5; i++)
				if (mPos.get(p.nom()).dirG())
					deplacement(COMMANDE.GAUCHE, posTmp);
				else
					deplacement(COMMANDE.DROITE, posTmp);
		}
	}

	private void gestionDegatJeter(PersonnageService p, ChoseService chose) {
		// pos = posP.clone
		PositionService pos = mPos.get(p.nom()).clone(), posVerif = pos.clone();

		for (int i = 0; i < 5; i++) {
			for (PersonnageService tmp : mPerso.values()) {
				if (tmp != p && tmp != chose)
					if (mPos.get(tmp.nom()).equals(pos)) {
						tmp.retraitPdv(chose.bonus());
					}
			}
			if (pos.dirG())
				deplacement(COMMANDE.GAUCHE, pos);
			else
				deplacement(COMMANDE.DROITE, pos);
			if (posVerif.equals(pos))
				break;
		}

		if (chose instanceof PersonnageService) {
			// perso porte -> perte pdv + deplacement
			PersonnageService persoPorte = ((PersonnageService) chose);
			persoPorte.retraitPdv(p.force());
			mFG.get(persoPorte.nom()).estGele = true;

			// on est assome une fois atteri
			mFG.get(persoPorte.nom()).nbGele = 3;
			mPos.get(((PersonnageService) chose).nom()).set(pos);
		} else {
			// depose de l'objet
			terrain.getBloc(pos.x(), pos.y(), pos.z()).deposerTresor(
					TYPE_Tresor.valueOf(((ObjetEquipable) chose).nom()));
		}
	}

	private void checkMort(Map<String, COMMANDE> cmd) {
		List<String> nomsMort = new ArrayList<String>();
		for (PersonnageService p : mPerso.values())
			if (p.estVaincu())
				nomsMort.add(p.nom());

		for (String nom : nomsMort) {
			mPos.remove(nom);
			cmd.remove(nom);
			mFG.remove(nom);
			mPerso.remove(nom);
		}
	}

	public String toString() {
		String s = "";
		for (PersonnageService perso : mPerso.values()) {
			s += mPos.get(perso.nom())
					+ "\t"
					+ perso
					+ ((mFG.get(perso.nom()).estGele) ? ((mFG.get(perso.nom()).nbGele > 1) ? " | gele "
							+ mFG.get(perso.nom()).nbGele
							: " | gele")
							: "") + "\n";
		}
		return s;
	}

	private void deplacement(COMMANDE cmd, PositionService pos) {
		// on atterit
		pos.setZ(0);

		int x = pos.x(), y = pos.y(), z = pos.z();

		// switch sur la cmd
		switch (cmd) {
		case GAUCHE:
			if (terrain.getBloc(Math.max(0, x - 1), y, z).typeBloc() == TYPE_Bloc.VIDE) {
				pos.setX(Math.max(0, pos.x() - 1));
				pos.setDir(true);
			}
			break;

		case DROITE:
			if (terrain.getBloc(Math.min(terrain.largeur() - 1, pos.x() + 1),
					y, z).typeBloc() == TYPE_Bloc.VIDE) {
				pos.setX(Math.min(terrain.largeur() - 1, pos.x() + 1));
				pos.setDir(false);
			}
			break;
		case HAUT:
			if (terrain.getBloc(x,
					Math.min(terrain.profondeur() - 1, pos.y() + 1), z)
					.typeBloc() == TYPE_Bloc.VIDE)
				pos.setY(Math.min(terrain.profondeur() - 1, pos.y() + 1));
			break;
		case BAS:
			if (terrain.getBloc(x, Math.max(0, pos.y() - 1), z).typeBloc() == TYPE_Bloc.VIDE)
				pos.setY(Math.max(0, pos.y() - 1));
			break;

		case SAUTER:
			if (terrain.getBloc(x, y, 1).typeBloc() == TYPE_Bloc.VIDE)
				pos.setZ(1);
			break;

		default:
			break;
		}
	}
}

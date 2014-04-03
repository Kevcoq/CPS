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

public class GestionCombat implements GestionCombatService {

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
	public boolean estFrappe(String nom) {
		if (mFG.containsKey(nom))
			return mFG.get(nom).estFrappe;
		throw new Error("nom n'existe pas");
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
		if (mFG.containsKey(nom))
			return mPos.get(nom);
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
			return lPerso;
		}
		throw new Error("nom n'existe pas");

	}

	@Override
	public void init(int largeur, int hauteur, int profondeur) {
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
		mFG = new HashMap<String, GestionCombat.FG>();
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
				// si estFrappe
				if (mFG.get(p.nom()).estFrappe) {
					// on cpt le nombre de degat recu
					int cpt = 0;
					List<PersonnageService> lCol = collision(p.nom());
					for (PersonnageService tmp : lCol)
						if (cmd.get(tmp.nom()) == COMMANDE.FRAPPE)
							cpt += tmp.force();

					// on les retire + gele
					p.retraitPdv(cpt);
					mFG.get(p.nom()).estGele = true;
					mFG.get(p.nom()).estFrappe = false;
					// TODO cpt gele

					// deplacement suivant la direction de frappe du 1er
					// agresseur
					if (mPos.get(lCol.get(0).nom()).dirG())
						pos.setX(Math.max(pos.x() - 5, 0));
					else
						pos.setX(Math.min(pos.x() + 5, terrain.largeur()));

				} else if (mFG.get(p.nom()).estGele) {
					// si gele
					if (--mFG.get(p.nom()).nbGele <= 0)
						mFG.get(p.nom()).estGele = false;

				} else {
					// pour le reste
					switch (cmd.get(p.nom())) {
					case GAUCHE:
						pos.setX(Math.max(0, pos.x() - 1));
						pos.setZ(0);
						pos.setDir(true);
						break;

					case DROITE:
						pos.setX(Math.min(terrain.largeur(), pos.x() + 1));
						pos.setZ(0);
						pos.setDir(false);
						break;
					case HAUT:
						pos.setY(Math.min(terrain.profondeur(), pos.y() + 1));
						pos.setZ(0);
						break;
					case BAS:
						pos.setY(Math.max(0, pos.y() - 1));
						pos.setZ(0);
						break;

					case SAUTER:
						pos.setZ(1);
						break;

					case FRAPPE:
						for (PersonnageService tmp : collision(p.nom()))
							mFG.get(tmp.nom()).estFrappe = true;
						mFG.get(p.nom()).estGele = true;
						pos.setZ(0);
						break;

					case JETER:
						if (p.estEquipe()) {
							ChoseService chose = p.laChoseEquipee();
							p.jeter();
							chose.estJete();

							// TODO gestion des degats au autre
							int cpt = 0;
							int direction;
							if (pos.dirG())
								direction = -1;
							else
								direction = 1;
							for (int i = 0; i < 5; i++) {
								for (String tmp : mPos.keySet())
									if (pos.equals(mPos.get(tmp).x()
											+ (i * direction), mPos.get(tmp)
											.y(), mPos.get(tmp).z())) {
										mPerso.get(tmp).retraitPdv(
												chose.bonus());
										cpt++;
									}
							}

							if (chose instanceof PersonnageService) {
								// perte pdv du perso jete + deplacement
								PersonnageService tmpPerso = (PersonnageService) chose;
								tmpPerso.retraitPdv(tmpPerso.bonus() * cpt);
								PositionService posTmpPerso = mPos.get(tmpPerso
										.nom());
								posTmpPerso.setX(Math.max(
										0,
										Math.min(terrain.largeur(), pos.x()
												+ (5 * direction))));
								posTmpPerso.setY(pos.y());
								posTmpPerso.setZ(0);
							} else {
								// depose de l'objet
								terrain.getBloc(
										Math.max(0, Math.min(terrain.largeur(),
												pos.x() + (5 * direction))),
										pos.y(), 0)
										.deposerTresor(
												TYPE_Tresor
														.valueOf(((ObjetEquipable) chose)
																.nom()));
							}
						}

						break;

					case RAMASSER:
						BlocService b = terrain.getBloc(pos.x(), pos.y(),
								pos.z());
						if (b.aTresor()) {
							ObjetEquipableService obj = new ObjetEquipable();
							// TODO bonus
							obj.init(b.typeTresor().name(), 50);
							obj.estRamasse();
							p.ramasser(obj);
						} else {
							for (String s : mPerso.keySet())
								if (pos.equals(mPos.get(s))) {
									PersonnageService tmp = mPerso.get(s);
									tmp.estRamasse();
									p.ramasser(tmp);
									break;
								}
						}
						break;

					default:
						pos.setZ(0);
						break;
					}
				}
			}
		}
	}
}

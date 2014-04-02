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
import services.TerrainService;
import enumeration.COMMANDE;
import enumeration.TYPE_Tresor;

public class GestionCombat implements GestionCombatService {

	class Position {
		private int x, y, z, dir;

		private Position(int x, int y, int direction) {
			super();
			this.x = x;
			this.y = y;
			this.z = 0;
			this.dir = direction;
		}

		private int[] toTbl() {
			int[] tmp = { x, y, z, dir };
			return tmp;
		}

		private boolean equals(Position p) {
			return x == p.x && y == p.y && z == p.z;
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
		Position pos1 = mPos.get(nom1), pos2 = mPos.get(nom2);
		return ((pos1.x == pos2.x || pos1.x == pos2.x - 1) && pos1.y == pos2.y && pos1.z == pos2.z);
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
				/ 2 - 10 - alex.profondeur() / 2, 1));

		PersonnageService ryan = new Personnage();
		ryan.init("Ryan", 25, 60, 12, 200, 1664);
		mPerso.put("Ryan", ryan);
		mPos.put("Ryan", new Position(10 + ryan.largeur() / 2 + 1, profondeur
				/ 2 + 10 - ryan.profondeur() / 2, 1));

		GangsterService slick = new Gangster();
		slick.init("Slick", 35, 80, 20, 250, 2000);
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
			Position pos = mPos.get(p.nom());
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
					mFG.get(p).estGele = true;
					mFG.get(p).estFrappe = false;
					// TODO cpt gele

					// deplacement suivant la direction de frappe du 1er
					// agresseur
					if (mPos.get(lCol.get(0).nom()).dir == 0)
						pos.x -= 5;
					else
						pos.x += 5;

				} else if (mFG.get(p.nom()).estGele) {
					// si gele
					if (--mFG.get(p.nom()).nbGele <= 0)
						mFG.get(p.nom()).estGele = false;

				} else {
					// pour le reste
					switch (cmd.get(p.nom())) {
					case GAUCHE:
						pos.x = Math.max(0, pos.x - 1);
						pos.z = 0;
						pos.dir = 0;
						break;

					case DROITE:
						pos.x = Math.max(terrain.largeur(), pos.x + 1);
						pos.z = 0;
						pos.dir = 1;
						break;
					case HAUT:
						pos.y = Math.max(terrain.profondeur(), pos.y + 1);
						pos.z = 0;
						break;
					case BAS:
						pos.y = Math.max(0, pos.y - 1);
						pos.z = 0;
						break;

					case SAUTER:
						pos.z = 1;
						break;

					case FRAPPE:
						for (PersonnageService tmp : collision(p.nom()))
							mFG.get(tmp.nom()).estFrappe = true;
						mFG.get(p.nom()).estGele = true;
						pos.z = 0;
						break;

					case JETER:
						if (p.estEquipe()) {
							ChoseService chose = p.laChoseEquipee();
							p.jeter();
							chose.estJete();

							// TODO gestion des degats au autre
							int cpt = 0;
							int direction;
							if (pos.dir == 0)
								direction = -1;
							else
								direction = 1;
							for (int i = 0; i < 5; i++) {
								for (String tmp : mPos.keySet())
									if (pos.x == mPos.get(tmp).x
											+ (i * direction)
											&& pos.y == mPos.get(tmp).y
											&& pos.z == mPos.get(tmp).z) {
										mPerso.get(tmp).retraitPdv(
												chose.bonus());
										cpt++;
									}
							}

							if (chose instanceof PersonnageService) {
								// perte pdv du perso jete + deplacement
								PersonnageService tmpPerso = (PersonnageService) chose;
								tmpPerso.retraitPdv(tmpPerso.bonus() * cpt);
								Position posTmpPerso = mPos.get(tmpPerso.nom());
								posTmpPerso.x = Math.max(
										0,
										Math.min(terrain.largeur(), pos.x
												+ (5 * direction)));
								posTmpPerso.y = pos.y;
								posTmpPerso.z = 0;
							} else {
								// depose de l'objet
								terrain.getBloc(
										Math.max(0, Math.min(terrain.largeur(),
												pos.x + (5 * direction))),
										pos.y, 0)
										.deposerTresor(
												TYPE_Tresor
														.valueOf(((ObjetEquipable) chose)
																.nom()));
							}
						}

						break;

					case RAMASSER:
						BlocService b = terrain.getBloc(pos.x, pos.y, pos.z);
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
						pos.z = 0;
						break;
					}
				}
			}
		}
	}
}

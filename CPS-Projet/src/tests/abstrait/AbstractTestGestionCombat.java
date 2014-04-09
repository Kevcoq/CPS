package tests.abstrait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.GestionCombatService;
import services.PersonnageService;
import services.PositionService;
import enumeration.COMMANDE;
import enumeration.TYPE_Bloc;

public abstract class AbstractTestGestionCombat {
	private GestionCombatService gestionCombat;

	protected AbstractTestGestionCombat() {
		gestionCombat = null;
	}

	protected final GestionCombatService getGestionCombat() {
		return gestionCombat;
	}

	protected final void setGestionCombat(GestionCombatService GestionCombat) {
		this.gestionCombat = GestionCombat;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		gestionCombat = null;
	}

	private void initialisation() {
		gestionCombat.init(20, 4, 10);
		for (int i = 0; i < gestionCombat.terrain().largeur(); i++)
			for (int j = 0; j < gestionCombat.terrain().profondeur(); j++)
				for (int k = 0; k < gestionCombat.terrain().hauteur(); k++)
					gestionCombat.terrain().getBloc(i, j, k)
							.init(TYPE_Bloc.VIDE);
	}

	// //////////////////////////////////////
	// /////////////// PRE //////////////////
	//
	//
	//
	// Init
	@Test
	public void testInit() {
		initialisation();
		Assert.assertTrue(true);
	}

	// //////////////////////////////////////
	// /////////////// POST /////////////////

	private boolean checkInvariant() {
		boolean bool = true;
		for (String s : gestionCombat.mPerso().keySet()) {
			List<PersonnageService> verif = gestionCombat.collision(s);
			for (PersonnageService pTmp : verif) {
				if (gestionCombat.collisionGauche(s, pTmp.nom()))
					bool &= gestionCombat.position(s).collision(
							gestionCombat.position(pTmp.nom()));
				else if (gestionCombat.collisionGauche(pTmp.nom(), s))
					bool &= gestionCombat.position(pTmp.nom()).collision(
							gestionCombat.position(s));
			}
			if (!bool) {
				Assert.assertTrue(gestionCombat.toString(), false);
				return false;
			}
		}
		return bool;
	}

	private boolean checkInit(Map<String, PersonnageService> perso, boolean pA,
			boolean pR, boolean pS) {
		if (perso.size() == 6 && perso.containsKey("Alex")
				&& perso.containsKey("Ryan") && perso.containsKey("Slick")) {
			// on verifie si ils sont gele ou frappe
			for (String tmp : perso.keySet())
				if (gestionCombat.estGele(tmp)) {
					if (!pA && tmp.equals("Alex"))
						continue;
					if (!pR && tmp.equals("Ryan"))
						continue;
					if (!pS && tmp.equals("Slick"))
						continue;
					Assert.assertTrue(tmp + " est gele.", false);

					return false;
				}
			Assert.assertTrue("personne de frappe ou gele.", true);

			// on verifie leur position
			PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
					.position("Ryan"), pSlick = gestionCombat.position("Slick");
			if ((!pA || pAlex.equals(0, 6, 0))
					&& (!pR || pRyan.equals(0, 4, 0))
					&& (!pS || pSlick.equals(19, 5, 0))) {
				Assert.assertTrue("tous a la bonne pos", true);
				return true;
			} else {
				Assert.assertTrue("position incorrecte", false);
				return false;
			}
		} else {
			Assert.assertTrue(
					"ne contient pas les 2 persos, slick et 3 gangster", false);
			return false;
		}
	}

	//
	//
	//
	// Init
	@Test
	public void testPostInit() {
		initialisation();

		// invariant
		if (checkInvariant()) {
			// recup map Perso
			Map<String, PersonnageService> perso = gestionCombat.mPerso();
			// on verifie si les persos y sont
			if (checkInit(perso, true, true, true)) {
				Assert.assertTrue(true);
				return;
			}
		}
	}

	@Test
	public void testPostGererRien() {
		initialisation();
		Map<String, COMMANDE> cmds = new HashMap<String, COMMANDE>();
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);

		gestionCombat.gerer(cmds);

		// invariant
		if (checkInvariant()) {
			// recup map Perso
			Map<String, PersonnageService> perso = gestionCombat.mPerso();
			// on verifie si les persos y sont
			if (checkInit(perso, true, true, true)) {
				Assert.assertTrue(true);
				return;
			}
		}
		Assert.assertTrue(false);
	}

	@Test
	public void testPostGererDroite() {
		initialisation();
		Map<String, COMMANDE> cmds = new HashMap<String, COMMANDE>();
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);

		cmds.put("Alex", COMMANDE.DROITE);
		gestionCombat.gerer(cmds);

		// invariant
		if (checkInvariant()) {
			// recup map Perso
			Map<String, PersonnageService> perso = gestionCombat.mPerso();
			// on verifie si les persos y sont
			if (checkInit(perso, false, true, true)) {
				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex");
				if (pAlex.equals(1, 6, 0)) {
					Assert.assertTrue(true);
					return;
				} else {
					Assert.assertTrue(gestionCombat.toString(), false);
				}
			}
		}
	}

	@Test
	public void testPostGererHaut() {
		initialisation();
		Map<String, COMMANDE> cmds = new HashMap<String, COMMANDE>();
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);

		cmds.put("Alex", COMMANDE.HAUT);
		gestionCombat.gerer(cmds);

		// invariant
		if (checkInvariant()) {
			// recup map Perso
			Map<String, PersonnageService> perso = gestionCombat.mPerso();
			// on verifie si les persos y sont
			if (checkInit(perso, false, true, true)) {

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex");
				if (pAlex.equals(0, 7, 0)) {
					Assert.assertTrue(true);
					return;
				} else {
					Assert.assertTrue(gestionCombat.toString(), false);
				}
			}
		}
	}

	@Test
	public void testPostGererBas() {
		initialisation();
		Map<String, COMMANDE> cmds = new HashMap<String, COMMANDE>();
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);

		cmds.put("Alex", COMMANDE.BAS);
		gestionCombat.gerer(cmds);

		// invariant
		if (checkInvariant()) {
			// recup map Perso
			Map<String, PersonnageService> perso = gestionCombat.mPerso();
			// on verifie si les persos y sont
			// on verifie si les persos y sont
			if (checkInit(perso, false, true, true)) {

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex");
				if (pAlex.equals(0, 5, 0)) {
					Assert.assertTrue(true);
					return;
				} else {
					Assert.assertTrue(gestionCombat.toString(), false);
				}
			}
		}
	}

	@Test
	public void testPostSauter() {
		initialisation();
		Map<String, COMMANDE> cmds = new HashMap<String, COMMANDE>();
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);

		cmds.put("Alex", COMMANDE.SAUTER);
		gestionCombat.gerer(cmds);

		// invariant
		if (checkInvariant()) {
			// recup map Perso
			Map<String, PersonnageService> perso = gestionCombat.mPerso();
			// on verifie si les persos y sont
			if (checkInit(perso, false, true, true)) {

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex");
				if (pAlex.equals(0, 6, 1)) {
					Assert.assertTrue(true);
					return;
				} else {
					Assert.assertTrue(gestionCombat.toString(), false);
				}
			}
		}
	}

	@Test
	public void testPostGererGauche() {
		initialisation();
		Map<String, COMMANDE> cmds = new HashMap<String, COMMANDE>();
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);

		cmds.put("Slick", COMMANDE.GAUCHE);
		gestionCombat.gerer(cmds);

		// invariant
		if (checkInvariant()) {
			// recup map Perso
			Map<String, PersonnageService> perso = gestionCombat.mPerso();
			// on verifie si les persos y sont
			if (checkInit(perso, true, true, false)) {

				// on verifie leur position
				PositionService pSlick = gestionCombat.position("Slick");
				if (pSlick.equals(18, 5, 0)) {
					Assert.assertTrue(true);
					return;
				} else {
					Assert.assertTrue(gestionCombat.toString(), false);
				}
			}
		}
	}

	@Test
	public void testPostGererRamasser() {
		initialisation();
		Map<String, COMMANDE> cmds = new HashMap<String, COMMANDE>();

		// bas
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);
		cmds.put("Alex", COMMANDE.BAS);
		gestionCombat.gerer(cmds);

		// bas
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);
		cmds.put("Alex", COMMANDE.BAS);
		gestionCombat.gerer(cmds);

		// ramasser
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);
		cmds.put("Alex", COMMANDE.RAMASSER);
		gestionCombat.gerer(cmds);

		// invariant
		if (checkInvariant()) {
			// recup map Perso
			Map<String, PersonnageService> perso = gestionCombat.mPerso();
			// on verifie si les persos y sont
			if (checkInit(perso, false, false, true)) {

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
						.position("Ryan");
				if (pAlex.equals(0, 4, 0) && pRyan.equals(0, 4, 1)) {
					if (perso.get("Ryan").estPorte()
							&& perso.get("Alex").estEquipe()) {
						Assert.assertTrue(true);
						return;
					} else {
						Assert.assertTrue(gestionCombat.toString(), false);
					}
				} else {
					Assert.assertTrue(gestionCombat.toString(), false);
				}
			}
		}
	}

	@Test
	public void testPostGererJeter() {
		initialisation();
		Map<String, COMMANDE> cmds = new HashMap<String, COMMANDE>();

		// bas
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);
		cmds.put("Alex", COMMANDE.BAS);
		gestionCombat.gerer(cmds);

		// bas
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);
		cmds.put("Alex", COMMANDE.BAS);
		gestionCombat.gerer(cmds);

		// ramasser
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);
		cmds.put("Alex", COMMANDE.RAMASSER);
		gestionCombat.gerer(cmds);

		// jeter
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);
		cmds.put("Alex", COMMANDE.JETER);
		gestionCombat.gerer(cmds);

		// invariant
		if (checkInvariant()) {
			// recup map Perso
			Map<String, PersonnageService> perso = gestionCombat.mPerso();
			// on verifie si les persos y sont
			if (checkInit(perso, false, false, true)) {

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
						.position("Ryan");

				if (pAlex.equals(0, 4, 0) && pRyan.equals(3, 4, 0))
					if (!perso.get("Ryan").estPorte()
							&& !perso.get("Alex").estEquipe()) {
						Assert.assertTrue(true);
						return;
					} else {
						Assert.assertTrue(gestionCombat.toString(), false);
					}
			}
		}
	}
}

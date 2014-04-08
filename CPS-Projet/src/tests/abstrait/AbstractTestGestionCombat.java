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

	// //////////////////////////////////////
	// /////////////// PRE //////////////////
	//
	//
	//
	// Init
	@Test
	public void testInit() {
		gestionCombat.init(20, 4, 10);
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
			if (!bool)
				return false;
		}
		return bool;
	}

	//
	//
	//
	// Init
	@Test
	public void testPostInit() {
		gestionCombat.init(20, 4, 10);

		// invariant
		if (checkInvariant()) {
			// recup map Perso
			Map<String, PersonnageService> perso = gestionCombat.mPerso();
			// on verifie si les persos y sont
			if (perso.size() == 6 && perso.containsKey("Alex")
					&& perso.containsKey("Ryan") && perso.containsKey("Slick")) {
				// on verifie si ils sont gele ou frappe
				for (String tmp : perso.keySet())
					if (gestionCombat.estFrappe(tmp)
							|| gestionCombat.estGele(tmp)) {
						Assert.assertTrue(false);
						return;
					}

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
						.position("Ryan"), pSlick = gestionCombat
						.position("Slick");
				if (pAlex.equals(0, 6, 0) && pRyan.equals(0, 4, 0)
						&& pSlick.equals(19, 5, 0)) {
					Assert.assertTrue(true);
					return;
				}
			}
		}
		Assert.assertTrue(false);
	}

	@Test
	public void testPostGererRien() {
		gestionCombat.init(20, 4, 10);
		Map<String, COMMANDE> cmds = new HashMap<String, COMMANDE>();
		for (String nom : gestionCombat.mPerso().keySet())
			cmds.put(nom, COMMANDE.RIEN);

		gestionCombat.gerer(cmds);

		// invariant
		if (checkInvariant()) {
			// recup map Perso
			Map<String, PersonnageService> perso = gestionCombat.mPerso();
			// on verifie si les persos y sont
			if (perso.size() == 6 && perso.containsKey("Alex")
					&& perso.containsKey("Ryan") && perso.containsKey("Slick")) {
				// on verifie si ils sont gele ou frappe
				for (String tmp : perso.keySet())
					if (gestionCombat.estFrappe(tmp)
							|| gestionCombat.estGele(tmp)) {
						Assert.assertTrue(false);
						return;
					}

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
						.position("Ryan"), pSlick = gestionCombat
						.position("Slick");
				if (pAlex.equals(0, 6, 0) && pRyan.equals(0, 4, 0)
						&& pSlick.equals(19, 5, 0))
					Assert.assertTrue(true);
				return;
			}
		}
		Assert.assertTrue(false);
	}

	@Test
	public void testPostGererDroite() {
		gestionCombat.init(20, 4, 10);
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
			if (perso.size() == 6 && perso.containsKey("Alex")
					&& perso.containsKey("Ryan") && perso.containsKey("Slick")) {
				// on verifie si ils sont gele ou frappe
				for (String tmp : perso.keySet())
					if (gestionCombat.estFrappe(tmp)
							|| gestionCombat.estGele(tmp)) {
						Assert.assertTrue(false);
						return;
					}

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
						.position("Ryan"), pSlick = gestionCombat
						.position("Slick");
				if (pAlex.equals(1, 6, 0) && pRyan.equals(0, 4, 0)
						&& pSlick.equals(19, 5, 0)) {
					Assert.assertTrue(true);
					return;
				}
			}
		}
		Assert.assertTrue(false);
	}

	@Test
	public void testPostGererHaut() {
		gestionCombat.init(20, 4, 10);
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
			if (perso.size() == 6 && perso.containsKey("Alex")
					&& perso.containsKey("Ryan") && perso.containsKey("Slick")) {
				// on verifie si ils sont gele ou frappe
				for (String tmp : perso.keySet())
					if (gestionCombat.estFrappe(tmp)
							|| gestionCombat.estGele(tmp)) {
						Assert.assertTrue(false);
						return;
					}

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
						.position("Ryan"), pSlick = gestionCombat
						.position("Slick");
				if (pAlex.equals(0, 7, 0) && pRyan.equals(0, 4, 0)
						&& pSlick.equals(19, 5, 0)) {
					Assert.assertTrue(true);
					return;
				}
			}
		}
		Assert.assertTrue(false);
	}

	@Test
	public void testPostGererBas() {
		gestionCombat.init(20, 4, 10);
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
			if (perso.size() == 6 && perso.containsKey("Alex")
					&& perso.containsKey("Ryan") && perso.containsKey("Slick")) {
				// on verifie si ils sont gele ou frappe
				for (String tmp : perso.keySet())
					if (gestionCombat.estFrappe(tmp)
							|| gestionCombat.estGele(tmp)) {
						Assert.assertTrue(false);
						return;
					}

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
						.position("Ryan"), pSlick = gestionCombat
						.position("Slick");
				if (pAlex.equals(0, 5, 0) && pRyan.equals(0, 4, 0)
						&& pSlick.equals(19, 5, 0)) {
					Assert.assertTrue(true);
					return;
				}
			}
		}
		Assert.assertTrue(false);
	}

	@Test
	public void testPostSauter() {
		gestionCombat.init(20, 4, 10);
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
			if (perso.size() == 6 && perso.containsKey("Alex")
					&& perso.containsKey("Ryan") && perso.containsKey("Slick")) {
				// on verifie si ils sont gele ou frappe
				for (String tmp : perso.keySet())
					if (gestionCombat.estFrappe(tmp)
							|| gestionCombat.estGele(tmp)) {
						Assert.assertTrue(false);
						return;
					}

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
						.position("Ryan"), pSlick = gestionCombat
						.position("Slick");
				if (pAlex.equals(0, 6, 1) && pRyan.equals(0, 4, 0)
						&& pSlick.equals(19, 5, 0)) {
					Assert.assertTrue(true);
					return;
				}
			}
		}
		Assert.assertTrue(false);
	}

	@Test
	public void testPostGererGauche() {
		gestionCombat.init(20, 4, 10);
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
			if (perso.size() == 6 && perso.containsKey("Alex")
					&& perso.containsKey("Ryan") && perso.containsKey("Slick")) {
				// on verifie si ils sont gele ou frappe
				for (String tmp : perso.keySet())
					if (gestionCombat.estFrappe(tmp)
							|| gestionCombat.estGele(tmp)) {
						Assert.assertTrue(false);
						return;
					}

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
						.position("Ryan"), pSlick = gestionCombat
						.position("Slick");
				if (pAlex.equals(0, 6, 0) && pRyan.equals(0, 4, 0)
						&& pSlick.equals(18, 5, 0)) {
					Assert.assertTrue(true);
					return;
				}
			}
		}
		Assert.assertTrue(false);
	}

	@Test
	public void testPostGererRamasser() {
		gestionCombat.init(20, 4, 10);
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
			if (perso.size() == 6 && perso.containsKey("Alex")
					&& perso.containsKey("Ryan") && perso.containsKey("Slick")) {
				// on verifie si ils sont gele ou frappe
				for (String tmp : perso.keySet())
					if (gestionCombat.estFrappe(tmp)
							|| gestionCombat.estGele(tmp)) {
						Assert.assertFalse(true);
						return;
					}

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
						.position("Ryan"), pSlick = gestionCombat
						.position("Slick");
				System.out.println(pAlex.equals(0, 4, 0) + "&&"
						+ pRyan.equals(0, 4, 1) + "&&"
						+ pSlick.equals(19, 5, 0));
				if (pAlex.equals(0, 4, 0) && pRyan.equals(0, 4, 1)
						&& pSlick.equals(19, 5, 0)) {
					System.out.println(perso.get("Ryan").estPorte() + "&&"
							+ perso.get("Alex").estEquipe());
					if (perso.get("Ryan").estPorte()
							&& perso.get("Alex").estEquipe()) {
						Assert.assertTrue(true);
						return;
					}
				}
			}
		}
		Assert.assertTrue(false);
	}

	@Test
	public void testPostGererJeter() {
		gestionCombat.init(20, 4, 10);
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
			if (perso.size() == 6 && perso.containsKey("Alex")
					&& perso.containsKey("Ryan") && perso.containsKey("Slick")) {
				// on verifie si ils sont gele ou frappe
				for (String tmp : perso.keySet())
					if (gestionCombat.estFrappe(tmp)
							|| gestionCombat.estGele(tmp)) {
						Assert.assertTrue(false);
						return;
					}

				// on verifie leur position
				PositionService pAlex = gestionCombat.position("Alex"), pRyan = gestionCombat
						.position("Ryan"), pSlick = gestionCombat
						.position("Slick");

				if (pAlex.equals(0, 4, 0) && pRyan.equals(3, 4, 0)
						&& pSlick.equals(19, 5, 0))
					if (!perso.get("Ryan").estPorte()
							&& !perso.get("Alex").estEquipe()) {
						Assert.assertTrue(true);
						return;
					}
			}
		}
		Assert.assertTrue(false);
	}
}

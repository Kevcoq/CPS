package contracts;

import services.MoteurJeuService;
import contracts.base.InvariantError;
import contracts.base.PostconditionError;
import contracts.base.PreconditionError;
import decorators.MoteurJeuDecorator;
import enumeration.COMMANDE;
import enumeration.RESULTAT;

public class MoteurJeuContract extends MoteurJeuDecorator {

	public MoteurJeuContract(MoteurJeuService moteurJeu) {
		super(moteurJeu);
	}

	public void checkInvariant() {
		// *** [invariants]
		// **** 0 ⩽ pasJeuCourant(M) ⩽ maxPasJeu(M)
		// **** estFini(M) =min
		// (Personnage::estVaincu(GestionCombat::alex(combat(M))) ∧
		// Personnage::estVaincu(GestionCombat::ryan(combat(M)))) ∨
		// Personnage::estVaincu(GestionCombat::slick(combat(M))) ∨
		// pasJeuCourant(M)=maxPasJeu(M))
		// **** resultatFinal(M) =min
		// ***** GAGNEE si
		// Personnage::estVaincu(GestionCombat::slick(combat(M))) ∧
		// ¬(Personnage::estVaincu(GestionCombat::alex(combat(M))) ∧
		// Personnage::estVaincu(GestionCombat::ryan(combat(M))))
		// ***** PERDUE si
		// ¬Personnage::estVaincu(GestionCombat::slick(combat(M))) ∧
		// (Personnage::estVaincu(GestionCombat::alex(combat(M))) ∧
		// Personnage::estVaincu(GestionCombat::ryan(combat(M))))
		// ***** NULLE sinon

		if (!(0 <= pasJeuCourant() && pasJeuCourant() <= maxPasJeu()))
			throw new InvariantError("moteurJeu");

		if (estFini()
				&& !((combat().mPerso().get("Alex").estVaincu() && combat()
						.mPerso().get("Ryan").estVaincu())
						|| combat().mPerso().get("Slick").estVaincu() || pasJeuCourant() == maxPasJeu()))
			throw new InvariantError("moteurJeu");

		if (estFini()) {
			if (!(combat().mPerso().get("Slick").estVaincu() && !(combat()
					.mPerso().get("Ryan").estVaincu() && combat().mPerso()
					.get("Alex").estVaincu()))
					&& resultatFinal() == RESULTAT.GAGNEE)
				throw new InvariantError("moteurJeu");

			if (!(!combat().mPerso().get("Slick").estVaincu() && (combat()
					.mPerso().get("Ryan").estVaincu() && combat().mPerso()
					.get("Alex").estVaincu()))
					&& resultatFinal() == RESULTAT.PERDUE)
				throw new InvariantError("moteurJeu");
		}
	}

	public void init(int largeur, int hauteur, int profondeur, int maxPas) {
		// ***** pre init(largeur, hauteur, profondeur, maxPas) require largeur
		// > 0 ∧ hauteur > 0 ∧ profondeur > 0 ∧ maxPas > 0
		if (!(largeur > 0 && hauteur > 0 && maxPas > 0))
			throw new PreconditionError("moteurJeu -> init");

		super.init(largeur, hauteur, profondeur, maxPas);
		checkInvariant();

		// *** [init]
		// **** maxPasJeu(init(l,h,p,m))=m
		// **** pasJeuCourant(init(l,h,p,m))=0
		// **** combat(init(l,h,p,m))=GestionCombat::init(l,h,p)
		if (!(maxPasJeu() == maxPas && pasJeuCourant() == 0))
			throw new PostconditionError("moteurJeu -> init");
	}

	public void pasJeu(COMMANDE cmdAlex, COMMANDE cmdRyan) {
		// ***** pre pasJeu(M, comAlex, comRyan) require ¬estFini(M) ∧ comAlex ∈
		// COMMANDE ∧ comRyan ∈ COMMANDE
		if (estFini())
			throw new PreconditionError("moteurJeu -> pasJeu");

		checkInvariant();

		int pasJeuCourant_atpre = pasJeuCourant();
		super.pasJeu(cmdAlex, cmdRyan);
		checkInvariant();

		// *** [pasJeu]
		// **** pasJeuCourant(pasJeu(M,cA,cR))=pasJeuCourant(M) +1
		// ****
		// combat(pasJeu(M,cA,cR))=GestionCombat::gerer(combat(M),Map<String,COMMANDE>)
		// ***** avec put("Alex", cA) ∧ put("Ryan", cR) ∧ ∀i
		// [0...GestionCombat::mPerso(combat(M)).size()-3] put(name,
		// randomCmd()) ∧ si (Math.random() < 0.1) alors put(randomName(),
		// randomCmd())
		if (!(pasJeuCourant() == pasJeuCourant_atpre + 1))
			throw new PostconditionError("moteurJeu -> pasJeu");
	}

	public void pasJeu(String nom, COMMANDE cmd) {
		// ***** pre pasJeu(M, nom, com) require ¬estFini(M) ∧ nom = ("Alex" ∨
		// "Ryan") ∧ com ∈ COMMANDE
		if (!(!estFini() && (nom.equals("Ryan") || nom.equals("Alex"))))
			throw new PreconditionError("moteurJeu -> pasJeu");

		checkInvariant();

		int pasJeuCourant_atpre = pasJeuCourant();
		super.pasJeu(nom, cmd);
		checkInvariant();

		// *** [pasJeu]
		// **** pasJeuCourant(pasJeu(M,n,c))=pasJeuCourant(M) +1
		// ****
		// combat(pasJeu(M,n,c))=GestionCombat::gerer(combat(M),Map<String,COMMANDE>)
		// ***** avec put(n, c) ∧ ∀i
		// [0...GestionCombat::mPerso(combat(M)).size()-2] put(name,
		// randomCmd()) ∧ si (Math.random() < 0.1) alors put(randomName(),
		// randomCmd())

		if (!(pasJeuCourant() == pasJeuCourant_atpre + 1))
			throw new PostconditionError("moteurJeu -> pasJeu");
	}
}

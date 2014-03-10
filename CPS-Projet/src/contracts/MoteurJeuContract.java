package contracts;

import services.MoteurJeuService;
import contracts.base.InvariantError;
import contracts.base.PostconditionError;
import contracts.base.PreconditionError;
import decorators.MoteurJeuDecorator;
import enumeration.COMMANDE;

public class MoteurJeuContract extends MoteurJeuDecorator {

	public MoteurJeuContract(MoteurJeuService moteurJeu) {
		super(moteurJeu);
	}

	public void checkInvariant() {
		// TODO estFini
		// 0 ≤ pasJeuCourant(M) ≤ maxPasJeu(M)
		// TODO resultatFInal =(min) WIN si boss_final = DCD | LOSE si
		// pointsDeVie = 0

		if (!(0 <= pasJeuCourant() && pasJeuCourant() <= maxPasJeu()))
			throw new InvariantError("moteurJeu");
	}

	public void init(int largeur, int hauteur, int maxPas) {
		// pre init(largeur,hauteur,maxPas) require largeur≥ 256 ∧ hauteur≥ 240
		// ∧ maxPas≥ 0
		if (!(largeur >= 256 && hauteur >= 240 && maxPas >= 0))
			throw new PreconditionError("moteurJeu -> init");

		super.init(largeur, hauteur, maxPas);
		checkInvariant();

		/*
		 * [init] maxPasJeu(init(l,h,m))=m pasJeuCourant(init(l,h,m))=0
		 * combat(init(l,h,m))=GestionCombat::init(l,h)
		 */
		if (!(maxPasJeu() == maxPas && pasJeuCourant() == 0))
			throw new PostconditionError("moteurJeu -> init");
	}

	public void pasJeu(COMMANDE cmd) {
		// pre pasJeu(M, cmd) require ¬estFini(M)
		if (estFini())
			throw new PreconditionError("moteurJeu -> pasJeu");

		checkInvariant();

		int pasJeuCourantPRE = pasJeuCourant();
		super.pasJeu(cmd);
		checkInvariant();

		/*
		 * [pasJeu] pasJeuCourant(pasJeu(M,cG,cR))=pasJeuCourant(M) +1
		 * combat(pasJeu(M,cG,cR))=GestionCombat::gerer(combat(M),cG,cR)
		 */
		if (!(pasJeuCourant() == pasJeuCourantPRE + 1))
			throw new PostconditionError("moteurJeu -> pasJeu");
	}
}

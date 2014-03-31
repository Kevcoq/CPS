package contracts;

import contracts.base.InvariantError;
import contracts.base.PostconditionError;
import contracts.base.PreconditionError;
import services.ObjetService;
import services.PersonnageService;
import decorators.PersonnageDecorator;

/**
 * Contrat du Personnage
 * 
 * @author Kevin & Quentin
 * 
 */
public class PersonnageContrat extends PersonnageDecorator {

	/**
	 * Constructeur
	 * 
	 * @param personnage
	 *            une implem de personnage
	 */
	public PersonnageContrat(PersonnageService personnage) {
		super(personnage);
	}

	private void checkInvariant() {

		// *** [invariants]
		// **** estVaincu(P) =(min) pointsDeVie(P) ≤ 0

		if (estVaincu()) {
			if (pointsDeVie() > 0)
				throw new InvariantError("personnage");
		}
	}

	@Override
	public void init(String nom, int largeur, int hauteur, int profondeur,
			int force, int pdv) {
		// pre init(nom,largeur,hauteur,profondeur,force,pointsVie) require nom
		// ≠ ""
		// ∧ largeur > 0 ∧ hauteur > 0 ∧ profondeur > 0 ∧ 0 < force < pointsVie
		if (!(!nom.isEmpty() && largeur > 0 && hauteur > 0 && profondeur > 0
				&& force > 0 && force < pdv)) {
			throw new PreconditionError("personnage -> init");
		}

		super.init(nom, largeur, hauteur, profondeur, force, pdv);
		checkInvariant();

		// *** [init]
		// **** nom(init(n,l,h,p,f,pv)) = n
		// **** largeur(init(n,l,h,p,f,pv)) = l
		// **** hauteur(init(n,l,h,p,f,pv)) = h
		// **** profondeur(init(n,l,h,p,f,pv)) = p
		// **** force(init(n,l,h,p,f,pv)) = f
		// **** pointsDeVie(init(n,l,h,p,f,pv)) = pv
		// **** sommeArgent(init(n,l,h,p,f,pv)) = 0
		// **** estEquipe(init(n,l,h,p,f,pv)) = false

		if (!(nom().equals(nom) && largeur() == largeur && hauteur() == hauteur
				&& profondeur() == profondeur && force() == force
				&& pointsDeVie() == pdv && sommeArgent() == 0
				&& estEquipe() == false && bonus() == force && !estPorte())) {
			throw new PostconditionError("personnage -> init");
		}
	}

	@Override
	public void retraitPdv(int s) {
		// pre retraitPdv(P,s) require ¬estVaincu(P) ∧ s > 0
		if (!(!estVaincu() && s > 0))
			throw new PreconditionError("personnage -> retraitPdv");

		checkInvariant();
		int pdv_atpre = pointsDeVie();
		int argent_atpre = sommeArgent();
		boolean equipe_atpre = estEquipe();
		ObjetService chose_atpre = laChoseEquipee();
		super.retraitPdv(s);
		checkInvariant();

		// *** [retraitPdv]
		// **** pointsDeVie(retraitPdv(P,s)) = pointsDeVie(P) - s
		// **** sommeArgent(retraitPdv(P,s)) = sommeArgent(P)
		// **** estEquipe(retraitPdv(P,s)) = estEquipe(P)
		// **** laChoseEquipee(retraitPdv(P,s)) = laChoseEquipee(P)

		if (!(pointsDeVie() == pdv_atpre - s && sommeArgent() == argent_atpre
				&& estEquipe() == equipe_atpre && laChoseEquipee() == chose_atpre))
			throw new PostconditionError("personnage -> retraitPdv");
	}

	@Override
	public void depotPdv(int s) {
		// pre depotPdv(P,s) require ¬estVaincu(P) ∧ s > 0
		if (!(!estVaincu() && s > 0))
			throw new PreconditionError("personnage -> depotPdv");

		checkInvariant();
		int pdv_atpre = pointsDeVie();
		int argent_atpre = sommeArgent();
		boolean equipe_atpre = estEquipe();
		ObjetService chose_atpre = laChoseEquipee();
		super.depotPdv(s);
		checkInvariant();

		// *** [depotPdv]
		// **** pointsDeVie(depotPdv(P,s)) = pointsDeVie(P) + s
		// **** sommeArgent(depotPdv(P,s)) = sommeArgent(P)
		// **** estEquipe(depotPdv(P,s)) = estEquipe(P)
		// **** laChoseEquipee(depotPdv(P,s)) = laChoseEquipee(P)
		if (!(pointsDeVie() == pdv_atpre + s && sommeArgent() == argent_atpre
				&& estEquipe() == equipe_atpre && laChoseEquipee() == chose_atpre))
			throw new PostconditionError("personnage -> depotPdv");
	}

	@Override
	public void retraitArgent(int s) {
		// pre retraitArgent(P,s) require ¬estVaincu(P) ∧ s > 0 ∧ s <
		// sommeArgent(P)
		if (!(!estVaincu() && s > 0 && s < sommeArgent()))
			throw new PreconditionError("personnage -> retraitArgent");

		checkInvariant();
		int pdv_atpre = pointsDeVie();
		int argent_atpre = sommeArgent();
		boolean equipe_atpre = estEquipe();
		ObjetService chose_atpre = laChoseEquipee();
		super.retraitArgent(s);
		checkInvariant();

		// *** [retraitArgent]
		// **** sommeArgent(retraitArgent(P,s)) = sommeArgent(P) - s
		// **** pointsDeVie(retraitArgent(P,s)) = pointsDeVie(P)
		// **** estEquipe(retraitArgent(P,s)) = estEquipe(P)
		// **** laChoseEquipee(retraitArgent(P,s)) = laChoseEquipee(P)
		if (!(pointsDeVie() == pdv_atpre && sommeArgent() == argent_atpre - s
				&& estEquipe() == equipe_atpre && laChoseEquipee() == chose_atpre))
			throw new PostconditionError("personnage -> retraitArgent");
	}

	@Override
	public void depotArgent(int s) {
		// pre depotArgent(P,s) require ¬estVaincu(P) ∧ s > 0
		if (!(!estVaincu() && s > 0))
			throw new PreconditionError("personnage -> depotArgent");

		checkInvariant();
		int pdv_atpre = pointsDeVie();
		int argent_atpre = sommeArgent();
		boolean equipe_atpre = estEquipe();
		ObjetService chose_atpre = laChoseEquipee();
		super.depotArgent(s);
		checkInvariant();

		// *** [depotArgent]
		// **** sommeArgent(depotArgent(P,s)) = sommeArgent(P) + s
		// **** pointsDeVie(depotArgent(P,s)) = pointsDeVie(P)
		// **** estEquipe(depotArgent(P,s)) = estEquipe(P)
		// **** laChoseEquipee(depotArgent(P,s)) = laChoseEquipee(P)
		if (!(pointsDeVie() == pdv_atpre && sommeArgent() == argent_atpre + s
				&& estEquipe() == equipe_atpre && laChoseEquipee() == chose_atpre))
			throw new PostconditionError("personnage -> depotArgent");
	}

	@Override
	public void ramasser(ObjetService o) {
		// pre ramasser(P,o) require ¬estVaincu(P) ∧ ¬estEquipe(P)
		if (!(!estVaincu() && !estEquipe()))
			throw new PreconditionError("personnage -> ramasser");

		checkInvariant();
		int pdv_atpre = pointsDeVie();
		int argent_atpre = sommeArgent();
		super.ramasser(o);
		checkInvariant();

		// *** [ramasser]
		// **** estEquipe(ramasser(P,o)) = true
		// **** laChoseEquipee(ramasser(P,o)) = o
		// **** sommeArgent(ramasser(P,o)) = sommeArgent(P)
		// **** pointsDeVie(ramasser(P,o)) = pointsDeVie(P)
		if (!(pointsDeVie() == pdv_atpre && sommeArgent() == argent_atpre
				&& estEquipe() == true && laChoseEquipee() == o))
			throw new PostconditionError("personnage -> ramasser");
	}

	@Override
	public void jeter() {
		// pre jeter(P) require ¬estVaincu(P) ∧ estEquipe(P)
		if (!(!estVaincu() && !estEquipe()))
			throw new PreconditionError("personnage -> jeter");

		checkInvariant();
		int pdv_atpre = pointsDeVie();
		int argent_atpre = sommeArgent();
		super.jeter();
		checkInvariant();

		// *** [jeter]
		// **** estEquipe(jeter(P)) = false
		// **** sommeArgent(jeter(P)) = sommeArgent(P)
		// **** pointsDeVie(jeter(P)) = pointsDeVie(P)
		if (!(pointsDeVie() == pdv_atpre && sommeArgent() == argent_atpre && estEquipe() == false))
			throw new PostconditionError("personnage -> jeter");
	}

	public void estRamasse() {
		if (!(!estPorte()))
			throw new PreconditionError("chose -> estRamsse");
		super.estRamasse();
		if (!(estPorte()))
			throw new PostconditionError("chose -> estRamasse");
	}

	public void estJete() {
		if (!(estPorte()))
			throw new PreconditionError("chose -> estJete");
		super.estJete();
		if (!(!estPorte()))
			throw new PostconditionError("chose -> estJete");
	}
}

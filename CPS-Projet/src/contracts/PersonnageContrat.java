package contracts;

import contracts.base.InvariantError;
import contracts.base.PostconditionError;
import contracts.base.PreconditionError;
import services.PersonnageService;
import decorators.PersonnageDecorator;

public class PersonnageContrat extends PersonnageDecorator {

	public PersonnageContrat(PersonnageService personnage) {
		super(personnage);
	}

	public void checkInvariant() {
		/*
		 * [invariants] min estVaincu(P) = pointsDeVie(P) ≤ 0
		 */
		if (estVaincu()) {
			if (pointsDeVie() > 0)
				throw new InvariantError("personnage");
		}
	}

	public void init(String nom, int largeur, int hauteur, int force, int pdv) {
		// pre init(nom,largeur,hauteur,force,pointsVie) require nom = "" ∧
		// largeur%2=1 ∧ hauteur%2=1 ∧ 0 < force < pointsVie
		if (nom.isEmpty() || largeur % 2 != 1 || hauteur % 2 != 1 || force <= 0
				|| force >= pdv) {
			throw new PreconditionError("personnage -> init");
		}

		super.init(nom, largeur, hauteur, force, pdv);
		checkInvariant();

		// post
		/*
		 * [init] nom(init(n,l,h,f,p))=n largeur(init(n,l,h,f,p))=l
		 * hauteur(init(n,l,h,f,p))=h force(init(n,l,h,f,p))=f
		 * pointsDeVie(init(n,l,h,f,p))=p
		 */
		if (nom().equals(nom) || largeur() != largeur || hauteur() != hauteur
				|| force() != force || pointsDeVie() != pdv) {
			throw new PostconditionError("personnage -> init");
		}
	}

	public void retrait(int s) {
		// pre retrait(P,s) require ¬estVaincu(P) ∧ s>0
		if (!(!estVaincu() && s > 0))
			throw new PreconditionError("personnage -> retrait");

		checkInvariant();
		int pdvPRE = pointsDeVie();
		super.retrait(s);
		checkInvariant();

		/*
		 * [retrait] pointsDeVie(retrait(P,s))=pointsDeVie(P) -s
		 */
		if (!(pointsDeVie() == pdvPRE - s))
			throw new PostconditionError("personnage -> retrait");
	}
}

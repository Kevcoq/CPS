package contracts;

import contracts.base.InvariantError;
import contracts.base.PostconditionError;
import services.GestionCombatService;
import decorators.GestionCombatDecorator;

public class GestionCombatContract extends GestionCombatDecorator {

	public GestionCombatContract(GestionCombatService gestion) {
		super(gestion);
	}

	public void checkInvariant(String nom) {
		// TODO
		if (!(collision(nom)))
			throw new InvariantError("gestionCombat");
	}

	public void init(int largeur, int hauteur) {
		super.init(largeur, hauteur);
		checkInvariant("Alex");
		checkInvariant("Ryan");

		/*
		 * [init] largeur(init(l,h))=l ∧ hauteur(init(l,h))=h
		 * guile(init(l,h))=Personnage::init("Guile",13,51,16,1664)
		 * ryu(init(l,h))=Personnage::init("Ryu",13,51,16,1664)
		 * estFrappé(init(l,h),id)=false estGelé(init(l,h),id)=false
		 */
		// TODO
		if (!(largeur() == largeur && hauteur() == hauteur
				&& !estFrappe("Alex") && !estFrappe("Ryan") && !estGele("Alex") && !estGele("Ryan")))
			throw new PostconditionError("gestionCombat -> init");

	}

	// TODO gerer
}

package contracts;

import java.util.ArrayList;
import java.util.List;

import services.GestionCombatService;
import services.PersonnageService;
import services.PositionService;
import contracts.base.InvariantError;
import contracts.base.PostconditionError;
import decorators.GestionCombatDecorator;

public class GestionCombatContract extends GestionCombatDecorator {

	public GestionCombatContract(GestionCombatService gestion) {
		super(gestion);
	}

	public void checkInvariant(String nom) {
		// *** [invariants]
		// **** collision(C, id) =min
		// ***** tmp = ∅, ∀nomP ∈ mPerso.keySet(),
		// ***** tmp ⋃ nomP si collisionGauche(C,id,nomP) ||
		// collisionGauche(C,nomP,id)
		//
		// **** collisionGauche(C,id1,id2) =min
		// ***** pos1 = position(C, id1), pos2 = position(C, id2),
		// ****** (pos1[0.] ⩽ pos2[0.] ⩽ pos1[0.] + 1) ∧
		// ****** (pos1[1.] = pos2[1.]) ∧
		// ****** (pos1[2.] = pos2[2.])
		List<PersonnageService> col = collision(nom);
		if (col.size() > 0) {
			List<PersonnageService> tmp = new ArrayList<>();
			for (String s : mPerso().keySet()) {
				if (collisionGauche(nom, s)) {
					PositionService pos1 = position(nom), pos2 = position(s);
					if (!(pos1.equals(pos2) || pos1.equals(pos2.x() - 1,
							pos2.y(), pos2.z())))
						throw new InvariantError("gestionCombat");
					tmp.add(mPerso().get(s));
				} else if (collisionGauche(s, nom)) {
					PositionService pos1 = position(s), pos2 = position(nom);
					if (!(pos1.equals(pos2) || pos1.equals(pos2.x() - 1,
							pos2.y(), pos2.z())))
						throw new InvariantError("gestionCombat");
					tmp.add(mPerso().get(s));
				}
			}
			tmp.remove(mPerso().get(nom));
			if (tmp.size() != col.size())
				throw new InvariantError("gestionCombat");
			else
				for (int i = 0; i < tmp.size(); i++)
					if (!tmp.contains(col.get(i)))
						throw new InvariantError("gestionCombat");
		}
	}

	public void init(int largeur, int hauteur, int profondeur) {
		super.init(largeur, hauteur, profondeur);
		checkInvariant("Alex");
		checkInvariant("Ryan");

		// *** [init]
		// **** terrain(init(l,h,p)) = Terrain::init(l,h,p)
		//
		// **** mPerso(init(l,h,p)) =
		// ***** mPerso.put("Alex", Personnage::init("Alex",20,51,10,100,1664))
		// ***** mPerso.put("Ryan", Personnage::init("Ryan",25,60,12,200,1664))
		// ***** mPerso.put("Slick", Gangster::init("Slick",35,80,20,250,2000))
		// ***** + 3 gangster lambda :
		// ****** name = RandomName()
		// ****** mPerso.put(name, Gangster::init(name,18,45,9,50,500))
		//
		// **** estFrappe(init(l,h,p),id)=false
		//
		// **** estGele(init(l,h,p),id)=false
		//
		// **** position
		// ***** position(init(l,h,p),"Alex") = { 0 ;
		// Terrain::profondeur(init(l,h,p)) / 2 + 1 ; 0 }
		// ***** position(init(l,h,p),"Ryan") = { 0 ;
		// Terrain::profondeur(init(l,h,p)) / 2 - 1 ; 0 }
		// ***** position(init(l,h,p),"Slick") = { Terrain::largeur(init(l,h,p))
		// - 1 ; Terrain::profondeur(init(l,h,p)) / 2 ; 0 }
		// ***** position(init(l,h,p), id) = Random sur Bloc Vide
		if (!(mPerso().size() == 6))
			throw new PostconditionError("gestionCombat -> init : SIZE");
		if (!(mPerso().containsKey("Alex") && mPerso().containsKey("Ryan") && mPerso()
				.containsKey("Slick")))
			throw new PostconditionError(
					"gestionCombat -> init : PERSO_PRESENT");

		for (String s : mPerso().keySet())
			if (estGele(s))
				throw new PostconditionError("gestionCombat -> init : GELE");

		PositionService posA = position("Alex"), posR = position("Ryan"), posS = position("Slick");
		if (!(posA.equals(0, terrain().profondeur() / 2 + 1, 0)
				&& posR.equals(0, terrain().profondeur() / 2 - 1, 0) && posS
					.equals(terrain().largeur() - 1,
							terrain().profondeur() / 2, 0)))
			throw new PostconditionError("gestionCombat -> init : POSITION");

	}
	// TODO gerer
}

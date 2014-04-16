package contracts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import services.GestionCombatService;
import services.PersonnageService;
import services.PositionService;
import contracts.base.InvariantError;
import contracts.base.PostconditionError;
import decorators.GestionCombatDecorator;
import enumeration.COMMANDE;

public class GestionCombatContract extends GestionCombatDecorator {

	public GestionCombatContract(GestionCombatService gestion) {
		super(gestion);
	}

	public void checkInvariant(String nom) {
		// *** [invariants]
		// **** collision(C, id) =min
		// ***** tmp = âˆ…, âˆ€nomP âˆˆ mPerso.keySet(),
		// ***** tmp â‹ƒ nomP si collisionGauche(C,id,nomP) ||
		// collisionGauche(C,nomP,id)
		//
		// **** collisionGauche(C,id1,id2) =min
		// ***** pos1 = position(C, id1), pos2 = position(C, id2),
		// ****** (pos1[0.] â©½ pos2[0.] â©½ pos1[0.] + 1) âˆ§
		// ****** (pos1[1.] = pos2[1.]) âˆ§
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

	// *** [gerer]
	// **** id n'appartient pas a keySet, creation d'un gangster
	// **** ∀id ∈ mPerso.keySet(), mPerso(gerer(C, cmd)).get(id) =
	// ***** si ¬estFrappe(gerer(C, cmd),id) alors mPerso(C).get(id)
	// ***** sinon Personnage::retrait(mPerso(C).get(id),cpt)
	// ****** avec cpt=0, ∀p ∈ collision(C,id), cmd.get(Personnage::nom(p)) ==
	// FRAPPE, cpt += Personnage::force(p)
	//
	// **** ∀id ∈ mPerso.keySet(), estFrappe(gerer(C, cmd), id) =
	// ***** collision(C,id) ≠ ∅ ∧ (∃p ∈ collision(C,id) tq
	// cmd.get(Personnage::nom(p)) == FRAPPE)
	//
	// **** ∀id ∈ mPerso.keySet(), estGele(gerer(C, cmd), id) = (cmd.get(id) ==
	// FRAPPE) ∨ estFrappe(gerer(C, cmd), id) ∨ Chose::estPorte(mPerso(gerer(C,
	// cmd)).get(id)) ∨ (estGele(C, id) ∧ cptGele(C, id) > 1)
	//
	// **** ∀id ∈ mPerso.keySet(), cptGele(gerer(C, cmd), id) =
	// ***** si (cmd.get(id) == FRAPPE) alors 1
	// ***** sinon si estFrappe(gerer(C, cmd), id) alors 3
	// ***** sinon cpt(C, id)-1
	//
	// **** mPerso.keySet(), position(gerer(C, cmd), id) =
	// ***** si estFrappe(gerer(C, cmd), id),
	// ****** si (∃p ∈ collisionGauche(C,id,p) tq cmd.get(Personnage::nom(p)) ==
	// FRAPPE) alors
	// ******* Position::setX(position(C, id), min(Position::x(position(C, id))
	// + 3, Terrain::largeur(terrain(C))))
	// ****** si (∃p ∈ collisionGauche(C,p,id) tq cmd.get(Personnage::nom(p)) ==
	// FRAPPE) alors
	// ******* Position::setX(position(C, id), max(Position::x(position(C, id))
	// - 3, 0))
	//
	// ***** si Chose::estPorte(mPerso(C).get(id)) alors
	// ****** ∃p ∈ mPerso(C).keySet() tq Personnage::laChoseEquipee(p) ==
	// mPerso(C).get(id) ∧ cmd.get(Personnage::nom(p)) ≠ JETER
	// ******* Position::set(position(C, id), position(C, p))
	// ****** ∃p ∈ mPerso(C).keySet() tq Personnage::laChoseEquipee(p) ==
	// mPerso(C).get(id) ∧ cmd.get(Personnage::nom(p)) == JETER
	// ******* si Position::dirG(position(gerer(C, cmd), p)) alors
	// Position::set(position(C, id), Position::x(max(position(C, p) - 5, 0)),
	// Position::y(position(C, p)), 0)
	// ******* sinon Position::set(position(C, id), Position::x(min(position(C,
	// p) + 5, Terrain::largeur(terrain(C))), Position::y(position(C, p)), 0)
	//
	// ***** si cmd.get(id) == DROITE
	// ****** Position::setX(position(C, id), min( Position::x(position(C, id))
	// + 1, Terrain::largeur(terrain(C))))
	// ****** Position::setDir(position(C, id), false);
	//
	// ***** si cmd.get(id) == GAUCHE
	// ****** Position::setX(position(C, id), max( Position::x(position(C, id))
	// - 1, 0))
	// ****** Position::setDir(position(C, id), true);
	//
	// ***** si cmd.get(id) == HAUT
	// ****** Position::setY(position(C, id), min( Position::y(position(C, id))
	// + 1, Terrain::profondeur(terrain(C))))
	//
	// ***** si cmd.get(id) == BAS
	// ****** Position::setY(position(C, id), max( Position::y(position(C, id))
	// - 1, 0))
	//
	// ***** si cmd.get(id) == SAUTER
	// ****** Position::setZ(position(C, id), 1)
	//
	// ***** sinon
	// ****** Position::setZ(position(C, id), 0)

	public void gerer(Map<String, COMMANDE> cmd) {
		super.gerer(cmd);
		for (String nom : cmd.keySet())
			checkInvariant(nom);
	}
}

package services;

import java.util.Map;

import enumeration.COMMANDE;

//
//import enumeration.COMMANDE;
//
public interface GestionCombatService {
	// observator :
	public TerrainService terrain();

	public Map<String, PersonnageService> mPerso();

	public boolean estFrappe(String nom);

	public boolean estGele(String nom);

	public int cptGele(String nom);

	public int[] position(String nom);

	public boolean collision(String nom);

	// constructor :
	public void init(int largeur, int hauteur, int profondeur);

	// operator :
	public void gerer(Map<String, COMMANDE> cmd);
}


// * GestionCombat
// ** service : GestionCombat
// ** use : [[file:terrain.org][Terrain,]] [[file:personnage.org][Personnage]]
// ** types : String, int, int[], boolean, Personnage[], Map<String,Personnage>,
// Map<String,COMMANDE>, enum COMMANDE {RIEN, GAUCHE, DROITE, HAUT, BAS, SAUTER,
// FRAPPE, JETER, RAMASSER}
//
//
// ** observators :
// **** terrain : [GestionCombat] → Terrain
//
// **** mPerso : [GestionCombat] → Map<String,Personnage>
//
// **** estFrappe : [GestionCombat] × String → boolean
// ***** pre estFrappe(C,id) require id ∈ mPerso.keySet()
//
// **** estGele : [GestionCombat] × String → boolean
// ***** pre estGele(C,id) require id ∈ mPerso.keySet()
//
// **** cptGele : [GestionCombat] × String → int
// ***** pre cptGele(C,id) require id ∈ mPerso.keySet() ∧ estGele(C,id)
//
// **** position : [GestionCombat] × String → int[] // 0 → x ∧ 1 → y ∧ 2 → z
// ***** pre position(C,id) require id ∈ mPerso.keySet()
//
// **** collisionGauche : [GestionCombat] × String × String → Personnage[]
// ***** pre collisionGauche(C,id1,id2) require id1 ∈ mPerso.keySet() ∧ id2 ∈
// mPerso.keySet()
//
// **** collision : [GestionCombat] × String → Personnage[]
// ***** pre collision(C,id) require id ∈ mPerso.keySet()
//
//
//
// ** Constructors :
// **** init : int × int × int → [GestionCombat]
//
//
//
//
// ** Operators :
// **** gerer : [GestionCombat] × Map<String,COMMANDE> → [GestionCombat]
//
//
//
//
// ** Observations :
// *** [invariants]
// **** collisionGauche(C,id1,id2) =min
// ***** dX = Personnage::largeur(mPerso.get(id1))/2 +
// Personnage::largeur(mPerso.get(id2))/2,
// ***** dY = Personnage::profondeur(mPerso.get(id1))/2 +
// Personnage::profondeur(mPerso.get(id2))/2,
// ***** dZ = Personnage::hauteur(mPerso.get(id1))/2 +
// Personnage::hauteur(mPerso.get(id2))/2,
// ***** si ↓ alors [id1,id2] sinon []
// ****** (-dX ⩽position(C,id2)[0.] - position(C,id1)[0.] ⩽ dX+1)
// ****** ∧ (-dY ⩽position(C,id2)[1.] - position(C,id1)[1.] ⩽ dY+1)
// ****** ∧ (-dZ ⩽position(C,id2)[2.] - position(C,id1)[2.] ⩽ dZ+1)
//
//
// **** collision(C, id) =min
// ***** tmp = ∅, ∀nomP ∈ mPerso.keySet(),
// ***** tmp ⋃ collisionGauche(C,id,nomP)[1.] ⋃ collisionGauche(C,nomP,id)[0.]
//
//
//
//
//
//
// *** [init]
// **** terrain(init(l,h,p)) = Terrain::init(l,h,p)
//
// **** mPerso(init(l,h,p)) =
// ***** mPerso.put("Alex", Personnage::init("Alex",20,51,10,100,1664))
// ***** mPerso.put("Ryan", Personnage::init("Ryan",25,60,12,200,1664))
// ***** mPerso.put("Slick", Gangster::init("Slick",35,80,20,250,2000))
// ***** + Random :
// ****** name = RandomName()
// ****** mPerso.put(name, Gangster::init(name,18,45,9,50,500))
//
// **** estFrappe(init(l,h,p),id)=false
//
// **** estGele(init(l,h,p),id)=false
//
// **** position
// ***** position(init(l,h,p),"Alex") = 10 +
// Personnage::largeur(mPerso(init(l,h,p)).get("Alex"))/2 + 1
// ***** position(init(l,h,p),"Ryan") = 10 +
// Personnage::largeur(mPerso(init(l,h,p)).get("Ryan"))/2 + 1
// ***** position(init(l,h,p),"Slick") = l - 10 -
// Personnage::largeur(mPerso(init(l,h,p)).get("Slick"))/2 - 1
// ***** position(init(l,h,p), id) = Random sur Bloc Vide
//
//
//
//
//
//
// *** [gerer]
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
// ******* { min(position(C, id)[0.]+64, Terrain::largeur(terrain(C)) ;
// position(C, id)[1.] ; 0 }
// ****** si (∃p ∈ collisionGauche(C,p,id) tq cmd.get(Personnage::nom(p)) ==
// FRAPPE) alors
// ******* { max(position(C, id)[0.]-64, 0) ; position(C, id)[1.] ; 0 }
// ***** si Chose::estPorte(mPerso(C).get(id)) alors
// ****** ∃p ∈ mPerso(C).keySet() tq Personnage::laChoseEquipee(p) ==
// mPerso(C).get(id) ∧ cmd.get(Personnage::nom(p)) ≠ JETER
// ******* { position(gerer(C, cmd), p)[0.] ; position(gerer(C,cmd), p)[1.] ;
// position(gerer(C, cmd), p)[2.] + Personnage::hauteur(p) }
// ****** ∃p ∈ mPerso(C).keySet() tq Personnage::laChoseEquipee(p) ==
// mPerso(C).get(id) ∧ cmd.get(Personnage::nom(p)) == JETER
// ******* TODO { position(gerer(C, cmd), p)[0.] ; position(gerer(C,cmd), p)[1.]
// ; 0 }
// ***** si cmd.get(id) == GAUCHE
// ******* { min( position(C, p)[0.] + 10, Terrain::largeur(terrain(C))) ;
// position(C, p)[1.] ; 0 }
// ***** si cmd.get(id) == DROITE
// ******* { max( position(C, p)[0.] - 10, 0) ; position(C, p)[1.] ; 0 }
// ***** si cmd.get(id) == HAUT
// ******* { position(C, p)[0.] ; min( position(C, p)[1.] + 10,
// Terrain::profondeur(terrain(C))) ; 0 }
// ***** si cmd.get(id) == BAS
// ******* { position(C, p)[0.] ; max( position(C, p)[1.] - 10, 0) ; 0 }
// ***** si cmd.get(id) == SAUTER
// ******* { position(C, p)[0.] ; position(C, p)[1.] ; 100 }
// ***** sinon
// ******* { position(C, p)[0.] ; position(C, p)[1.] ; 0 }


package services;

import java.util.List;
import java.util.Map;

import enumeration.COMMANDE;

/**
 * Interface GestionCombat
 * @author Kevin & Quentin
 *
 */
public interface GestionCombatService {
		
	/* Observators */
	
	public TerrainService terrain();

	public Map<String, PersonnageService> mPerso();

	/**
	 * pre estFrappe(C,id) require id ∈ mPerso.keySet()
	 * @param nom
	 * @return
	 */
	//public boolean estFrappe(String nom);

	/**
	 * pre estFrappe(C,id) require id ∈ mPerso.keySet()
	 * @param nom
	 * @return
	 */
	public boolean estGele(String nom);

	/**
	 * pre cptGele(C,id) require id ∈ mPerso.keySet() ∧ estGele(C,id)
	 * @param nom
	 * @return
	 */
	public int cptGele(String nom);

	/**
	 * pre position(C,id) require id ∈ mPerso.keySet() 
	 * @param nom
	 * @return
	 */
	public PositionService position(String nom);

	/**
	 * pre position(C,id) require id ∈ mPerso.keySet() 
	 * @param nom
	 * @return
	 */
	public List<PersonnageService> collision(String nom);

	/**
	 * pre collisionGauche(C,id1,id2) require  id1 ∈ mPerso.keySet() 
	 * ∧ id2 ∈ mPerso.keySet()
	 * @param nom1
	 * @param nom2
	 * @return
	 */
	public boolean collisionGauche(String nom1, String nom2);

	
	
	/* Constructor */
	public void init(int largeur, int hauteur, int profondeur);

	
	
	/* Operators */
	public void gerer(Map<String, COMMANDE> cmd);
	
	
	/* Observations */
	
//	*** [invariants]
//	**** collision(C, id) =min 
//	***** tmp = ∅, ∀nomP ∈ mPerso.keySet(),
//	***** tmp ⋃ nomP si collisionGauche(C,id,nomP) || collisionGauche(C,nomP,id) 
//
//	**** collisionGauche(C,id1,id2) =min
//	***** pos1 = position(C, id1), pos2 = position(C, id2),
//	***** Position::collision(pos1, pos2)
//
//
//	*** [init]
//	**** terrain(init(l,h,p)) = Terrain::init(l,h,p)
//
//	**** mPerso(init(l,h,p)) = 
//	***** mPerso.put("Alex", Personnage::init("Alex",20,51,10,100,1664)) 
//	***** mPerso.put("Ryan", Personnage::init("Ryan",25,60,12,200,1664)) 
//	***** mPerso.put("Slick", Gangster::init("Slick",35,80,20,250,2000))
//	***** + 3 gangster lambda :
//	****** name = RandomName()
//	****** mPerso.put(name, Gangster::init(name,18,45,9,50,500))
//
//	**** estFrappe(init(l,h,p),id)=false
//
//	**** estGele(init(l,h,p),id)=false
//
//	**** position
//	***** position(init(l,h,p),"Alex") = 
//			Position::init(0, Terrain::profondeur(init(l,h,p)) / 2 + 1, 0, false)
//	***** position(init(l,h,p),"Ryan") = 
//			Position::init(0, Terrain::profondeur(init(l,h,p)) / 2 - 1, 0, false)
//	***** position(init(l,h,p),"Slick") = 
//			Position::init(Terrain::largeur(init(l,h,p)) - 1, 
//						   Terrain::profondeur(init(l,h,p)) / 2, 0, true)
//	***** position(init(l,h,p), id) = Random sur Bloc Vide 
//	
//	
//	
//	*** [gerer]
//	**** id n'appartient pas a keySet, creation d'un gangster
//	****  ∀id ∈ mPerso.keySet(), mPerso(gerer(C, cmd)).get(id) =  
//	*****	 si ¬estFrappe(gerer(C, cmd),id) alors mPerso(C).get(id)
//	***** 	 sinon Personnage::retrait(mPerso(C).get(id),cpt)
//	****** avec cpt = 0, 
//				∀p ∈ collision(C,id), 
//					cmd.get(Personnage::nom(p)) == FRAPPE, 
//					cpt += Personnage::force(p) 
//
//	**** ∀id ∈ mPerso.keySet(), estFrappe(gerer(C, cmd), id) =
//		  collision(C,id) ≠ ∅ 
//		  ∧ (∃p ∈ collision(C,id) tq cmd.get(Personnage::nom(p)) == FRAPPE)
//
//	**** ∀id ∈ mPerso.keySet(), estGele(gerer(C, cmd), id) = 
//		  cmd.get(id) == FRAPPE 
//		  ∨ estFrappe(gerer(C, cmd), id) 
//		  ∨ Chose::estPorte(mPerso(gerer(C, cmd)).get(id)) 
//		  ∨ (estGele(C, id) ∧ cptGele(C, id) > 1)
//
//	**** ∀id ∈ mPerso.keySet(), cptGele(gerer(C, cmd), id) = 
//	***** si (cmd.get(id) == FRAPPE) alors 1 
//	***** sinon si estFrappe(gerer(C, cmd), id) alors 3
//	***** sinon cpt(C, id)-1
//
//	**** mPerso.keySet(), position(gerer(C, cmd), id) =
//	***** si estFrappe(gerer(C, cmd), id), 
//	****** si (∃p ∈ collisionGauche(C,id,p) tq cmd.get(Personnage::nom(p)) == FRAPPE) 
//			alors Position::setX(position(C, id), 
//					min(Position::x(position(C, id)) + 3, 
//					Terrain::largeur(terrain(C))))
//	****** si (∃p ∈ collisionGauche(C,p,id) tq cmd.get(Personnage::nom(p)) == FRAPPE) 
//			alors Position::setX(position(C, id), 
//					max(Position::x(position(C, id)) - 3,
//					0))
//
//	***** si Chose::estPorte(mPerso(C).get(id)) alors 
//	****** ∃p ∈ mPerso(C).keySet() tq 
//			Personnage::laChoseEquipee(p) == mPerso(C).get(id) 
//			∧ cmd.get(Personnage::nom(p)) ≠ JETER
//	******* Position::set(position(C, id), position(C, p))
//	****** ∃p ∈ mPerso(C).keySet() tq 
//			Personnage::laChoseEquipee(p) == mPerso(C).get(id) 
//			∧ cmd.get(Personnage::nom(p)) == JETER
//	******* si Position::dirG(position(gerer(C, cmd), p)) 
//			 alors Position::set(position(C, id), 
//					Position::x(max(position(C, p) - 5, 0)), 
//					Position::y(position(C, p)), 0) 
//	*******  sinon Position::set(position(C, id), 
//					Position::x(min(position(C, p) + 5, 
//					Terrain::largeur(terrain(C))), 
//					Position::y(position(C, p)), 0) 
//
//	***** si cmd.get(id) == DROITE
//	****** Position::setX(position(C, id), 
//			min( Position::x(position(C, id)) + 1, 
//			Terrain::largeur(terrain(C))))
//	****** Position::setDir(position(C, id), false);
//
//	***** si cmd.get(id) == GAUCHE
//	****** Position::setX(position(C, id), 
//			max( Position::x(position(C, id)) - 1, 0))
//	****** Position::setDir(position(C, id), true);
//
//	***** si cmd.get(id) == HAUT
//	****** Position::setY(position(C, id), 
//			min( Position::y(position(C, id)) + 1, 
//			Terrain::profondeur(terrain(C))))
//
//	***** si cmd.get(id) == BAS
//	****** Position::setY(position(C, id), max( Position::y(position(C, id)) - 1, 0))
//
//	***** si cmd.get(id) == SAUTER 
//	****** Position::setZ(position(C, id), 1)
//
//	***** sinon 
//	****** Position::setZ(position(C, id), 0)
	
}

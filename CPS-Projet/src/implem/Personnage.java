package implem;

import services.ChoseService;
import services.PersonnageService;

/**
 * Implem du Personnage
 * 
 * @author Kevin & Quentin
 * 
 */
public class Personnage extends Chose implements PersonnageService {

	private String nom;
	private int largeur;
	private int hauteur;
	private int profondeur;
	private int force;
	private int pdv;
	private int argent;
	private ChoseService obj;

	@Override
	public String nom() {
		return nom;
	}

	@Override
	public int largeur() {
		return largeur;
	}

	@Override
	public int hauteur() {
		return hauteur;
	}

	@Override
	public int profondeur() {
		return profondeur;
	}

	@Override
	public int force() {
		return force;
	}

	@Override
	public int pointsDeVie() {
		return pdv;
	}

	@Override
	public boolean estVaincu() {
		return pdv == 0;
	}

	@Override
	public int sommeArgent() {
		return argent;
	}

	@Override
	public boolean estEquipe() {
		return obj != null;
	}

	@Override
	public ChoseService laChoseEquipee() {
		return obj;
	}

	@Override
	public void init(String nom, int largeur, int hauteur, int profondeur,
			int force, int pdv) {
		init(force);
		this.nom = nom;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.profondeur = profondeur;
		this.force = force;
		this.pdv = pdv;
	}

	@Override
	public void retraitPdv(int s) {
		pdv -= s;
	}

	@Override
	public void depotPdv(int s) {
		pdv += s;
	}

	@Override
	public void retraitArgent(int s) {
		argent -= s;
	}

	@Override
	public void depotArgent(int s) {
		argent += s;
	}

	@Override
	public void ramasser(ChoseService o) {
		obj = o;
	}

	@Override
	public void jeter() {
		obj = null;
	}

}

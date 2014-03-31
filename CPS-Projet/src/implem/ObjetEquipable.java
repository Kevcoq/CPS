package implem;

import services.ObjetEquipableService;

public class ObjetEquipable extends Chose implements ObjetEquipableService {
	private String nom = "";

	@Override
	public String nom() {
		return nom;
	}

	@Override
	public void init(String nom) {
		this.nom = nom;
	}

}

package implem;

import services.ObjetService;

public class Objet implements ObjetService {
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

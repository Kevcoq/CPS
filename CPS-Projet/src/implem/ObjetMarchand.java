package implem;

import services.ObjetMarchandService;

public class ObjetMarchand extends Objet implements ObjetMarchandService {
	private int prix = 0;
	private boolean estVendu = false;

	@Override
	public int prix() {
		return prix;
	}

	@Override
	public boolean estVendu() {
		return estVendu;
	}

	@Override
	public void init(String nom, int prix) {
		init(nom);
		this.prix = prix;
		estVendu = false;
	}

	@Override
	public void vendre() {
		estVendu = true;
	}

}

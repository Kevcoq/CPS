package decorators;

import services.ObjetMarchandService;

public abstract class ObjetMarchandDecorator implements ObjetMarchandService {
	private ObjetMarchandService obj;

	public ObjetMarchandDecorator(ObjetMarchandService obj) {
		super();
		this.obj = obj;
	}

	/**
	 * @return
	 * @see services.ObjetMarchand#prix()
	 */
	public int prix() {
		return obj.prix();
	}

	/**
	 * @return
	 * @see services.ObjetService#nom()
	 */
	public String nom() {
		return obj.nom();
	}

	/**
	 * @return
	 * @see services.ObjetMarchand#estVendu()
	 */
	public boolean estVendu() {
		return obj.estVendu();
	}

	/**
	 * @param nom
	 * @param prix
	 * @see services.ObjetMarchand#init(String, int)
	 */
	public void init(String nom, int prix) {
		obj.init(nom, prix);
	}

	/**
	 * @param nom
	 * @see services.ObjetService#init(java.lang.String)
	 */
	public void init(String nom) {
		obj.init(nom);
	}

	/**
	 * 
	 * @see services.ObjetMarchand#vendre()
	 */
	public void vendre() {
		obj.vendre();
	}

}

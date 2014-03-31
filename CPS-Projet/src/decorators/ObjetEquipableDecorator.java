package decorators;

import services.ObjetEquipableService;

public abstract class ObjetEquipableDecorator implements ObjetEquipableService {
	private ObjetEquipableService objE;

	public ObjetEquipableDecorator(ObjetEquipableService objE) {
		super();
		this.objE = objE;
	}

	/**
	 * @return
	 * @see services.ObjetService#nom()
	 */
	public String nom() {
		return objE.nom();
	}

	/**
	 * @return
	 * @see services.ChoseService#bonus()
	 */
	public int bonus() {
		return objE.bonus();
	}

	/**
	 * @param nom
	 * @see services.ObjetService#init(java.lang.String)
	 */
	public void init(String nom) {
		objE.init(nom);
	}

	/**
	 * @return
	 * @see services.ChoseService#estPorte()
	 */
	public boolean estPorte() {
		return objE.estPorte();
	}

	/**
	 * @param b
	 * @see services.ChoseService#init(int)
	 */
	public void init(int b) {
		objE.init(b);
	}

	/**
	 * 
	 * @see services.ChoseService#estRamasse()
	 */
	public void estRamasse() {
		objE.estRamasse();
	}

	/**
	 * 
	 * @see services.ChoseService#estJete()
	 */
	public void estJete() {
		objE.estJete();
	}

	public void init(String nom, int b) {
		init(b);
		init(nom);
	}

}

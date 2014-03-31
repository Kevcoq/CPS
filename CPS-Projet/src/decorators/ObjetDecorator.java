package decorators;

import services.ObjetService;

public abstract class ObjetDecorator implements ObjetService {
	private ObjetService obj;

	public ObjetDecorator(ObjetService obj) {
		super();
		this.obj = obj;
	}

	/**
	 * @return
	 * @see services.ObjetService#nom()
	 */
	public String nom() {
		return obj.nom();
	}

	/**
	 * @param nom
	 * @see services.ObjetService#init(java.lang.String)
	 */
	public void init(String nom) {
		obj.init(nom);
	}
	
	
	
}

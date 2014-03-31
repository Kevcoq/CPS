package decorators;

import services.ChoseService;

public abstract class ChoseDecorator implements ChoseService{
	private ChoseService chose;

	
	
	public ChoseDecorator(ChoseService chose) {
		super();
		this.chose = chose;
	}

	/**
	 * @return
	 * @see services.ChoseService#bonus()
	 */
	public int bonus() {
		return chose.bonus();
	}

	/**
	 * @return
	 * @see services.ChoseService#estPorte()
	 */
	public boolean estPorte() {
		return chose.estPorte();
	}

	/**
	 * 
	 * @see services.ChoseService#init()
	 */
	public void init(int b) {
		chose.init(b);
	}

	/**
	 * 
	 * @see services.ChoseService#estRamasse()
	 */
	public void estRamasse() {
		chose.estRamasse();
	}

	/**
	 * 
	 * @see services.ChoseService#estJete()
	 */
	public void estJete() {
		chose.estJete();
	}
	
	
}

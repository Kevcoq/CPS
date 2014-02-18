package bridge.services;

public interface RoadSectionService {
	/** Observateur : nombre de véhicules sur la section de route */
	public int getNbCars();
	
	// inv: getNbCars() >= 0
	
	/** Initialisation
	 * post: getNbCars() == 0
	 */
	public void init();
	
	/** Entrée d'un véhicule
	 * post: getNbCars() == getNbCars()@pre + 1 
	 */
	public void enter();

	/** Sortie d'un véhicule
	 * pre: getNbCars() > 0
	 * post: getNbCars() == getNbCars()@pre -1 1 
	 */
	public void leave();

}

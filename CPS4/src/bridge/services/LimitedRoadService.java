package bridge.services;

public interface LimitedRoadService extends /* include */ RoadSectionService {
	/** Observateur : nombre max. de voitures sur la section limitée */
	public int getLimit();
	/** Observateur : pont plein ? */
	public boolean isFull();
	
	// inv: isFull() == (getNbCars() == getLimit())
	// inv: getNbCars() <= getLimit()
	
	/** Initialisation
	 * pre: lim>0
	 * post: getLimit() == lim
	 */
	public void init(int lim);
	
	/**
	 * Redéfinition de l'entrée
	 * pre: !isFull()
	 * Remarque : on n'a pas  true ==> !iFull()  donc LimitedRoadService ne raffine pas RoadSectionService
	 */
	public void enter();
	
}

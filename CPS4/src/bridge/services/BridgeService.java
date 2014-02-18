package bridge.services;

public interface BridgeService extends /* refine */ LimitedRoadService {
	/** Observateur: nombre de véhicule sur le pont en direction de l'ile */
	public int getNbIn();
	
	/** Observateur: nombre de véhicule sur le pont en direction du continent */
	public int getNbOut();

	// inv: getNbCars() == getNbIn() + getNbOut()
	// inv: getNbIn() >= 0
	// inv: getNbOut() >= 0
	
	/** Initialisation
	 *  pre: lim > 0
	 *  post: getNbIn() == 0
	 *  post: getNbOut() == 0
	 */
	public void init(int lim);
	
	/** Entrée depuis le continent
	 * pre: !isFull()
	 * post: getNbIn() == getNbIn()@pre + 1
	 * post: getNbOut() == getNbOut()@pre
	 */
	public void enterIn();
	
	/** Sortie vers l'île
	 * pre: getNbIn() > 0
	 * post: getNbIn() == getNbIn()@pre - 1
	 * post: getNbOut() == getNbOut()@pre
	 */
	public void leaveIn();
	
	/** Entrée depuis l'île
	 * pre: !isFull()
	 * post: getNbOut() == getNbOut()@pre + 1
	 * post: getNbIn() == getNbIn()@pre
	 */
	public void enterOut();
	
	/** Sortie vers le continent
	 * pre: getNbOut() > 0
	 * post: getNbOut() == getNbOut()@pre - 1
	 * post: getNbIn() == getNbIn()@pre
	 */
	public void leaveOut();
	
	/** Redéfinition obligatoire de l'entrée "classique" pour
	 * safisfaire le raffinement.
	 * pre: !isFull()
	 * post: if getNbIn()@pre > getNbOut()@pre
	 *       then
	 *         getNbIn() == getNbIn()@pre
	 *         getNbOut() == getNbOut()@pre + 1 
	 *       else
	 *         getNbIn() == getNbIn()@pre + 1
	 *         getNbOut() == getNbOut()@pre 
	 */
	public void enter();
	
	/** Redéfinition obligatoire de la sortie "classique" pour
	 * safisfaire le raffinement.
	 * pre: getNbCars() > 0
	 * post: if getNbIn()@pre > getNbOut()@pre
	 *       then
	 *         getNbIn() == getNbIn()@pre - 1
	 *         getNbOut() == getNbOut()@pre
	 *       else
	 *         getNbIn() == getNbIn()@pre
	 *         getNbOut() == getNbOut()@pre - 1
	 */
	public void leave();
	
}

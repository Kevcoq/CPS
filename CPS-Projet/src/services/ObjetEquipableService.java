package services;

/**
 * Interface ObjetEquipable
 * @author Kevin & Quentin
 *
 */
public interface ObjetEquipableService extends /* refine */ ChoseService, ObjetService {
	
	/* Constructor */
	/**
	 * pre init(nom) require nom ≠ ""
	 * @param nom
	 * @param bonus
	 */
	public void init(String nom, int bonus);
}

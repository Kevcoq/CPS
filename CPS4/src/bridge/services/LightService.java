package bridge.services;

public interface LightService {
	/** observateur: la lumière est allumée ? */
	public boolean isOn();
	
	/** initialisation
	 * post: isOn()==false
	 */
	public void init();
	
	/** allumage
	 * pre: !isOn()
	 * post: isOn() == true
	 */
	public void switchOn();
	
	/** extinction
	 * pre: isOn()
	 * post: isOn() == false
	 */
	public void switchOff();
}

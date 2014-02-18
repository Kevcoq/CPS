package bridge.services;

public interface TrafficLightService extends /* refine */ LightService {
	/** observateur: le feu est vert ? 
	 * pre: isOn() 
	 */
	public boolean isGreen();
	/** observateur: le feu est rouge ?
	 * pre: isOn()
	 */
	public boolean isRed();
	
	// inv: isGreen() xor isRed()
	
	public void init();
	
	/** passer au vert.
	 * pre: isRed()
	 * post: isRed()==false
	 * post: isGreen()==true
	 */
	public void changeGreen();
	
	/** passer au rouge.
	 * pre: isGreen()
	 * post: isGreen()==false
	 * post: isRed()==true
	 */	
	public void changeRed();
}

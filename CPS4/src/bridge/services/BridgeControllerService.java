package bridge.services;

public interface BridgeControllerService {
	public boolean canEnter();
	public boolean canLeave();
	public void control();
}

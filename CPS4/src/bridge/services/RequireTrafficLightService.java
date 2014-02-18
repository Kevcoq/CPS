package bridge.services;

public interface RequireTrafficLightService {
	// remarque : liaison nommée
	public void bindTrafficLightService(String name, TrafficLightService service);
}

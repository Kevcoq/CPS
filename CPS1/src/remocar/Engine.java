package remocar;

/*******************************************
 * Copyright (C) 2005, Frédéric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.BasicComponent;
import tamago.ServiceBindException;

public class Engine extends BasicComponent implements EngineService,
		RequireEffectorEngineService, RequirePuissanceService {

	// Required services
	private EffectorEngineService effector;
	private PuissanceService puissance;

	// properties
	private double max_thrust = 100.0;
	private double throttle = 0.9;

	// Interface fonctionnelle
	public Engine() {
		effector = null;
	}

	public void forward() {
		if (puissance.use(2)) {
			System.err.println("[ENGINE] : Delivered Forward POWER=" + throttle
					* max_thrust);
			effector.deliverPower(throttle * max_thrust);
		}
	}

	public void backward() {
		if (puissance.use(2)) {
			System.err.println("[ENGINE] : Delivered Backward POWER="
					+ throttle * max_thrust);
			effector.deliverPower(-throttle * max_thrust);
		}
	}

	// Laison des fournisseurs
	public void bindEffectorEngineService(EffectorEngineService effector)
			throws ServiceBindException {
		this.effector = effector;
	}

	// Cycle de vie

	// Propriét�s
	public void setMaxThrust(double max_thrust) {
		this.max_thrust = max_thrust;
	}

	public double getMaxThrust() {
		return max_thrust;
	}

	public void setThrottle(double max_thrust) {
		this.throttle = max_thrust;
	}

	public double getThrottle() {
		return throttle;
	}

	@Override
	public void bindPuissanceService(PuissanceService puissance)
			throws ServiceBindException {
		this.puissance = puissance;
	}

}

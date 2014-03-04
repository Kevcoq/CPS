package maleva;

import tamago.*;

public class Trouillard extends Agent {

	/* inner components */
	CapteurPred capteur;
	FuirAgent fuire;
	Effecteur effecteur;
	RandomMouvement rndmvt;

	public Trouillard(String id) {
		super(id, TYPE_PROIE);

		capteur = null;
		fuire = null;
		effecteur = null;
		rndmvt = null;
	}

	/* properties */
	public int getViewDist() {
		return capteur.getViewDist();
	}

	@Override
	public void setViewDist(int vdist) {
		capteur.setViewDist(vdist);
	}

	/* external bindings */

	public void bindEnvironmentFetchService(EnvironmentFetchService efs)
			throws ServiceBindException {
		if (capteur == null)
			throw new ServiceBindException(
					"Cannot bind Service : capteur inner component not initialized");
		capteur.bindEnvironmentFetchService(efs);
	}

	public void bindEnvironmentEffectService(EnvironmentEffectService ees)
			throws ServiceBindException {
		if (effecteur == null)
			throw new ServiceBindException(
					"Cannot bind Service : effecteur inner component not initialized");
		effecteur.bindEnvironmentEffectService(ees);
	}

	public boolean isBound() {
		return super.isBound() && capteur.isBound() && effecteur.isBound();
	}

	/* LCInitializeController */

	public void initialize() throws LifeCycleException {
		super.initialize();

		// create inner components
		capteur = new CapteurPred(this);
		fuire = new FuirAgent(this);
		rndmvt = new RandomMouvement(this);
		effecteur = new Effecteur(this);

		// inner bindings
		try {
			fuire.bindAgentRefListService(capteur);
			effecteur.bindActionService(fuire);
			effecteur.bindActionService(rndmvt);
		} catch (ServiceBindException sbe) {
			initialized = false;
			LifeCycleException lce = new LifeCycleException(
					"Service bind exception");
			lce.initCause(sbe);
			throw lce;
		}
	}

	public boolean step() throws LifeCycleException {
		super.step();

		if (capteur.step() && fuire.step()) {
			System.out.println("Trouillard '" + getID() + "' sees agents, escape");
			return effecteur.step();
		} else if (rndmvt.step()) {
			System.out.println("Agent '" + getID()
					+ "' sees nothing or cannot escape");
			return effecteur.step();
		}
		System.out.println("Agent '" + getID() + "' cannot do nothing");
		return false;
	}
}
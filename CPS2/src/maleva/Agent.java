package maleva;

import tamago.*;

public abstract class Agent extends BasicComposite implements /* requires */
RequireEnvironmentFetchService, RequireEnvironmentRegisterService,

/* controllers */
LCInitializeController, LCConfigureAgentController, LCStepperController {
	public static final int TYPE_AGENT = 1;
	public static final int TYPE_PROIE = 2;
	public static final int TYPE_PRED = 3;

	/* fields */
	protected boolean initialized;    
	private boolean configured;
	private EnvironmentRegisterService ers;

	/* properties */
	private int type;
	private String id;
	private int angle;
	private int speed;
	private int fov;
	private int posx;
	private int posy;

	/* inner components */
	// none in the abstract class of agents

	public Agent(String id, int type) {
		this.type = type;
		initialized = false;
		configured = false;
		this.id = id;

		angle = 0;
		speed = 5;
		fov = 65;
		posx = 0;
		posy = 0;
	}

	/* properties */

	public String getID() {
		return id;
	}

	public int getType() {
		return type;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle % 360;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFieldOfView() {
		return fov;
	}

	public void setFieldOfView(int fov) {
		this.fov = fov % 360;
	}

	public int getPosX() {
		return posx;
	}

	public void setPosX(int posx) {
		this.posx = posx;
	}

	public int getPosY() {
		return posy;
	}

	public void setPosY(int posy) {
		this.posy = posy;
	}

	// must be implemented in subclasses through inner components
	public abstract int getViewDist();

	public abstract void setViewDist(int vdist);

	/* external bindings */
	public abstract void bindEnvironmentFetchService(EnvironmentFetchService efs)
			throws ServiceBindException;

	public void bindEnvironmentRegisterService(EnvironmentRegisterService ers)
			throws ServiceBindException {
		this.ers = ers;
	}

	public abstract void bindEnvironmentEffectService(
			EnvironmentEffectService ees) throws ServiceBindException;

	public boolean isBound() {
		return ers != null;
	}

	/* controllers */

	public void initialize() throws LifeCycleException {
		if (initialized)
			throw new LifeCycleException(
					"Cannot initialize : agent already initialized");
		initialized = true;
	}

	public void configure(Environnement env, int x, int y)
			throws LifeCycleException {
		if (!initialized)
			throw new LifeCycleException(
					"Cannot configure : agent not initialized");
		if (configured)
			throw new LifeCycleException(
					"Cannot configure : agent already configured");

		try {
			bindEnvironmentRegisterService(env);
			bindEnvironmentFetchService(env);
			bindEnvironmentEffectService(env);
		} catch (ServiceBindException sbe) {
			configured = false;
			LifeCycleException lce = new LifeCycleException(
					"Service bind exception");
			lce.initCause(sbe);
			throw lce;
		}

		posx = x;
		posy = y;
		ers.registerAgent(getID(), getPosX(), getPosY(), getType());

		configured = true;
	}

	public boolean step() throws LifeCycleException {
		if (!initialized)
			throw new LifeCycleException("Cannot step : agent not initialized");
		if (!configured)
			throw new LifeCycleException("Cannot step : agent not configured");
		return false;
	}
}

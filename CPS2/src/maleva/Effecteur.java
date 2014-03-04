
package maleva;

import tamago.*;

import java.util.*;

public class Effecteur extends BasicComponent 
implements /* requires */
RequireActionService,	       
RequireEnvironmentEffectService,
/* controllers */
AgentPartController,
LCStepperController {
	
	/* fields */
	protected Agent outer;
	
	/* required services */
	private ArrayList<ActionService> as;
	private EnvironmentEffectService ees;
	
	public Effecteur(Agent outer) {
		this.outer = outer;
		as = new ArrayList<ActionService>();
		ees = null;
	}
	
	public void bindActionService(ActionService as) throws ServiceBindException {
		if(!this.as.contains(as)) {
			this.as.add(as);
		}
	}
	
	public void bindEnvironmentEffectService(EnvironmentEffectService ees) throws ServiceBindException {
		this.ees = ees;
	}
	
	public boolean isBound() {
		return as!=null && ees!=null;
	}
	
	/* Stepper controller */
	
	public static int computeDeltaX(int x, int speed, int angle) {
		double rad = Math.PI/180.0 * angle;
		int nx = (int) (Math.cos(rad)*speed) + x;
		return nx;
	}
	
	public static int computeDeltaY(int y, int speed, int angle) {
		double rad = Math.PI/180.0 * angle;
		int ny = (int) (Math.sin(rad)*speed) + y;
		return ny;
	}
	
	public boolean step() throws LifeCycleException {
		if(!isBound())
			throw new LifeCycleException("Effecteur component not correctly bound");
		
		Action act = null;
		for(ActionService a : as) {
			act = a.getAction();
			if(act!=null)
				break;
		}
		System.out.println("[Effecteur] Received action = "+act);
		if(act==null)
			return false;
		
		if(act.isMove()) {
			System.out.println("X = "+outer.getPosX());
			System.out.println("Y = "+outer.getPosY());
			System.out.println("SPEED = "+outer.getSpeed());
			System.out.println("ANGLE = "+outer.getAngle());
			int nx = computeDeltaX(outer.getPosX(),outer.getSpeed(),act.getAngle());
			int ny = computeDeltaY(outer.getPosY(),outer.getSpeed(),act.getAngle());
			ees.moveAgent(outer.getID(),nx,ny,outer.getType());
			outer.setPosX(nx);
			outer.setPosY(ny);
			System.out.println("X' = "+outer.getPosX());
			System.out.println("Y' = "+outer.getPosY());
			return true;
		}
		
		return false;
		
	}
}

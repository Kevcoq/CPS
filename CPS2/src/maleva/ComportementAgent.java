
package maleva;

import tamago.*;

public abstract class ComportementAgent extends BasicComponent 
implements /* provides */
ActionService,
/* requires */
RequireAgentRefListService,
/* controllers */
AgentPartController,
LCStepperController {
	
	/* fields */
	protected Agent outer;
	protected Action action;
	
	/* required services */
	protected AgentRefListService arls;
	
	public ComportementAgent(Agent outer) {
		this.outer = outer;
		action = null;
		arls = null;
	}
	
	public void bindAgentRefListService(AgentRefListService arls) {
		this.arls = arls;
	}
	
	public boolean isBound() {
		return arls!=null;
	}
	
	/* ActionService functionalities */
	
	public Action getAction() {
		return action;
	}
	
	protected static int computeAngle(AgentRef[] arefs, int add_angle) {
		int mindist = Integer.MAX_VALUE;
		int maxdist = 0;
		int closest_ref = 0;
		for(int i=0;i<arefs.length;i++) {
			if(arefs[i].getDist()>maxdist)
				maxdist = arefs[i].getDist();
			if(arefs[i].getDist()<mindist) {
				mindist = arefs[i].getDist();
				closest_ref = i;
			}
		}
		
		double dist_coef = 0.9 / (maxdist-mindist);
		// coefficient is 90% for closest agent
		// 10% for farthest
		
		int angle = (arefs[closest_ref].getAngle()) % 360;
		for(int i=0;i<arefs.length;i++) {
			// calculer nouvel angle
			int loc_angle = (arefs[i].getAngle()) % 360;
			if(loc_angle>angle)
				angle+= (int) (loc_angle-angle)* (dist_coef*arefs[i].getDist());
			else
				angle-= (int) (angle-loc_angle)* (dist_coef*arefs[i].getDist());
		}
		return angle;
	}
	
	/* Stepper controller */
	
	public abstract boolean step() throws LifeCycleException;
}

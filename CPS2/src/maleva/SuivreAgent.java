
package maleva;

import tamago.*;

public class SuivreAgent extends ComportementAgent {
	
	public SuivreAgent(Agent outer) {
		super(outer);
	}
	
	/* Stepper controller */
	
	public boolean step() throws LifeCycleException {
		if(arls==null)
			throw new LifeCycleException("AgentRefListService not bound");
		AgentRef[] arefs = arls.getAgentRefs();
		if(arefs==null) {
			action = null; // cannot take any decision
			// System.out.println("SuivreAgent failed for "+outer.getID());
			return false; // step not finished
		}
		
		action = new Action(Action.MOVE,ComportementAgent.computeAngle(arefs,0));
		return true; // step finished
	}
}
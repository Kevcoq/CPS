
package maleva;

import tamago.*;

import java.util.*;

public class CapteurPred extends CapteurAgent {

    public CapteurPred(Agent outer) {
	super(outer);
    }
	       
    /* LCActivateController interface */
    public boolean step() throws LifeCycleException {
	if(efs==null)
	    throw new LifeCycleException("Environment fetch service not bound");
	last_seen_agents = efs.fetchPreds(outer.getID(), outer.getPosX(),outer.getPosY(),outer.getAngle(),outer.getFieldOfView(),getViewDist());
	if(last_seen_agents==null || last_seen_agents.length==0)
	    return false;
	else
	    return true;
    }
}

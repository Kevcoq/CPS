
package maleva;

import tamago.*;

import java.util.*;

public class Environnement extends BasicComponent 
implements /* provides */
EnvironmentFetchService,
EnvironmentRegisterService,
EnvironmentEffectService {
	
	/* fields */
	private HashMap<String,AgentSight> agents;
	
	/* properties */
	private int width;
	private int height;
	
	public Environnement(int width, int height) {
		agents = new HashMap<String,AgentSight>();
		this.width = width;
		this.height = height;
	}
	
	public Environnement() {
		this(65536,65536);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void registerAgent(String id, int posx, int posy, int type) {
		agents.put(id, new AgentSight(posx,posy,type));
	}
	
	public void unregisterAgent(String id) {
		agents.remove(id);
	}
	
	public void moveAgent(String id, int posx, int posy, int type) {
		agents.put(id, new AgentSight(posx,posy,type));
	}
	
	protected static int computeDist(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt( Math.abs(x1-x2)*Math.abs(x1-x2) + Math.abs(y1-y2)*Math.abs(y1-y2));
	}
	
	/* EnvironmentFetchService functionalities */
	
	protected AgentRef[] fetchAgentsByType(int type,String from_id, int fromx, int fromy, int vangle, int fov, int vdist) {
		ArrayList<AgentRef> vagents = new ArrayList<AgentRef>();
		for(String key : agents.keySet()) {
			if(!key.equals(from_id)) {
				AgentSight sight = agents.get(key);
				System.out.println("sight.getX() = "+sight.getX());
				System.out.println("sight.getY() = "+sight.getY());
				System.out.println("fromx = "+fromx);
				System.out.println("fromy = "+fromy);
				int dist = computeDist(sight.getX(),sight.getY(),fromx,fromy);
				if(dist<vdist) {
					int angle = (int) ((180.0/Math.PI)*Math.acos(computeDist(sight.getX(),sight.getY(),fromx,sight.getY())/dist)) % 360;
					if(angle<0)
						angle+=360;
					if(type==Agent.TYPE_AGENT || type==sight.getType())
						vagents.add( new AgentRef(key,sight.getX(),sight.getY(),angle,dist));
				}
			}
		}
		
		if(vagents.size()==0)
			return null;
		
		AgentRef[] arefs = new AgentRef[vagents.size()];
		
		for(int i=0;i<vagents.size();i++)
			arefs[i] = vagents.get(i);
		
		return arefs;
	}
	
	public AgentRef[] fetchAgents(String from_id, int fromx, int fromy, int angle, int fov, int vdist) {
		return fetchAgentsByType(Agent.TYPE_AGENT,from_id,fromx,fromy,angle,fov,vdist);
	}
	
	public AgentRef[] fetchProies(String from_id, int fromx, int fromy, int angle, int fov, int vdist) {
		return fetchAgentsByType(Agent.TYPE_PROIE,from_id,fromx,fromy,angle,fov,vdist);
	}
	
	public AgentRef[] fetchPreds(String from_id, int fromx, int fromy, int angle, int fov, int vdist) {
		return fetchAgentsByType(Agent.TYPE_PRED,from_id,fromx,fromy,angle,fov,vdist);
	}
	
	private class AgentSight {
		private int x, y, type;
		
		public AgentSight(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public int getType() {
			return type;
		}
		
		public boolean isProie() {
			return type==Agent.TYPE_PROIE;
		}
		
		public boolean isPred() {
			return type==Agent.TYPE_PRED;
		}
		
	}
}

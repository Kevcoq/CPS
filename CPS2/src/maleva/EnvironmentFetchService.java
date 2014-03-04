
package maleva;

import tamago.*;

public interface EnvironmentFetchService extends Service {
    AgentRef[] fetchAgents(String from_id, int fromx, int fromy, int angle, int fov, int vdist);
    AgentRef[] fetchProies(String from_id, int fromx, int fromy, int angle, int fov, int vdist);
    AgentRef[] fetchPreds(String from_id, int fromx, int fromy, int angle, int fov, int vdist);
}

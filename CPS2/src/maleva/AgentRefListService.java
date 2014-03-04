
package maleva;

import tamago.*;

public interface AgentRefListService extends Service {
    AgentRef[] getAgentRefs();
    int getNbAgentRefs();
}
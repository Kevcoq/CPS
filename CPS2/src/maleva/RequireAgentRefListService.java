
package maleva;

import tamago.*;

public interface RequireAgentRefListService extends RequireService {
    void bindAgentRefListService(AgentRefListService arls) throws ServiceBindException;
}


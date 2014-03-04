
package maleva;

import tamago.*;

public interface RequireActionService extends RequireService {
    void bindActionService(ActionService as) throws ServiceBindException;
}


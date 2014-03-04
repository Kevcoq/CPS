
package maleva;

import tamago.*;

public interface RequireEnvironmentFetchService extends RequireService {
    void bindEnvironmentFetchService(EnvironmentFetchService ees) throws ServiceBindException;
}


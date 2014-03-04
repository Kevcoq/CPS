
package maleva;

import tamago.*;

public interface RequireEnvironmentRegisterService extends RequireService {
    void bindEnvironmentRegisterService(EnvironmentRegisterService ees) throws ServiceBindException;
}

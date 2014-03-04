
package maleva;

import tamago.*;

public interface RequireEnvironmentEffectService extends RequireService {
    void bindEnvironmentEffectService(EnvironmentEffectService ees) throws ServiceBindException;
}

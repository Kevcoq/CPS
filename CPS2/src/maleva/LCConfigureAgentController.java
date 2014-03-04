
package maleva;

import tamago.*;

public interface LCConfigureAgentController extends LifeCycleController {
    void configure(Environnement env, int x, int y) throws LifeCycleException;
}

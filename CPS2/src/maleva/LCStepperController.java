
package maleva;

import tamago.*;

public interface LCStepperController extends LifeCycleController {
    boolean step() throws LifeCycleException;
}


package maleva;

import tamago.*;

public interface EnvironmentEffectService extends Service {
    void moveAgent(String id, int posx, int posy, int type);
}

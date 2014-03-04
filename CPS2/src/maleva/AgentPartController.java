
package maleva;

import tamago.*;

public interface AgentPartController extends Controller {
    // this is a simple maker
    // however, this implies two things (that should be verifiable):
    // 1) the implementor accept a  Composite (or subclass) argument
    // 2) an outer reference is globally available in the part component
    //    so there are three special references:
    //    1. this  : the self object
    //    2. super : the superclass part of this
    //    3. outer : the reference to the enclosing composite
}


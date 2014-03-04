package echo;

import tamago.BasicComponent;

public class ConsoleErr extends BasicComponent
    implements ConsoleService {

    /* attributes */

    // private data

    // properties

    // bindings

    /* construction */

    public ConsoleErr() {
    }

    /* require methods */

    /* provide methods */

    public void print(String s) {
	System.err.print(s);
    }

    public void println(String s) {
	System.err.println(s);
    }

}

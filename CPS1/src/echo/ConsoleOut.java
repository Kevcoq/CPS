package echo;

import tamago.BasicComponent;

public class ConsoleOut extends BasicComponent
    implements ConsoleService{//, RequireEchoService {

    /* attributes */
	//private List<EchoService> echo;

    // private data

    // properties

    // bindings

    /* construction */

    public ConsoleOut() {
    	//echo = new ArrayList<>();
    }

    /* require methods */

    /* provide methods */

    public void print(String s) {
	System.out.print(s);
    }

    public void println(String s) {
	System.out.println(s);
    }

//	@Override
//	public void bindEchoService(EchoService echo) throws ServiceBindException {
//		this.echo.add(echo);
//	}

}

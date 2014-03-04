package echo;

import tamago.ServiceBindException;

public class HelloWorldMain {

	public static void main (String [] args) throws ServiceBindException {
	

		ConsoleOut cs = new ConsoleOut();
		EchoServer es = new EchoServer();
		
		es.bindConsoleService(cs);
		es.echo("Hello World !");
		
	}
	
	
	
	
}

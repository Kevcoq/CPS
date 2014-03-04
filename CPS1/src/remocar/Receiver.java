package remocar;

import tamago.BasicComponent;
import tamago.ServiceBindException;

public class Receiver extends BasicComponent
    implements ReceiverService, 
	       RequireEngineService,
	       RequireDirectionService {

    // Required services
    private EngineService engine;
    private DirectionService direction;

    // Interface fonctionnelle
    public Receiver() {
        engine = null;
        direction = null;
    }
    
    public void receive(Command command) {
	System.err.println("[RECEIVER] : received Command : " + command);

        if(command.isForward()) {
            engine.forward();
	}

        if(command.isBackward()) {
            engine.backward();
	}

        if(command.isLeft()) {
            direction.goLeft();
	}

        if(command.isRight()) {
            direction.goRight();
	}
    }


    // Laison des fournisseurs
    public void bindEngineService(EngineService engine) throws ServiceBindException {
        this.engine = engine;
    }

    public void bindDirectionService(DirectionService direction) throws ServiceBindException {
	this.direction = direction;
    }
    
}

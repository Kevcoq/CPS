package remocar;

/*******************************************
 * Copyright (C) 2005, Fr�d�ric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.BasicComponent;
import tamago.ServiceBindException;

public class Remote extends BasicComponent implements RemoteService,
		RequireReceiverService, RequirePuissanceService {

	// Required services
	private ReceiverService receiver;
	private PuissanceService puissance;
	private Battery battery;

	// Attributes
	private int next_command;

	// Interface fonctionnelle
	public Remote() {
		receiver = null;
		next_command = Command.RESET;
		battery = new Battery();
		this.bindPuissanceService(battery);
	}

	public void on() {
		System.err.println("[REMOTE] : goes on()");
	}

	public void off() {
		System.err.println("[REMOTE] : goes off()");
	}

	public void forward() {

		if (puissance.use(1)) {
			System.err.println("[REMOTE] : select forward()");
			next_command |= Command.FORWARD;
		}
	}

	public void backward() {
		if (puissance.use(1)) {
			System.err.println("[REMOTE] : select backward()");
			next_command |= Command.BACKWARD;
		}
	}

	public void left() {
		if (puissance.use(1)) {
			System.err.println("[REMOTE] : select left()");
			next_command |= Command.LEFT;
		}
	}

	public void right() {
		if (puissance.use(1)) {
			System.err.println("[REMOTE] : select right()");
			next_command |= Command.RIGHT;
		}
	}

	public void reset() {
		System.err.println("[REMOTE] : select reset()");
		next_command = Command.RESET;
	}

	public void activate() {
		System.err.println("[REMOTE] : activated");
		receiver.receive(new Command(next_command));
	}

	// Laison des fournisseurs
	public void bindReceiverService(ReceiverService receiver)
			throws ServiceBindException {
		this.receiver = receiver;
	}

	@Override
	public void bindPuissanceService(PuissanceService puissance)
			throws ServiceBindException {
		this.puissance = puissance;
	}

}

package remocar;
/*******************************************
 * Copyright (C) 2005, Fr�d�ric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.BasicComposite;
import tamago.ServiceBindException;

public class Car extends BasicComposite
       implements CarService,
		  ExportReceiverService {

    // sub components
    private Receiver receiver;
    private Engine engine;
    private Direction direction;
    private Effector effector;
    private Battery battery;

    // Interface fonctionnelle
    public Car() throws ServiceBindException {
    	battery = FactoryBattery.newBattery(3, 10);
	receiver = new Receiver();
        engine = new Engine();
	direction = new Direction();
	effector = new Effector();
	receiver.bindEngineService(engine);
	receiver.bindDirectionService(direction);
	engine.bindEffectorEngineService(effector);
	engine.bindPuissanceService(battery);
	direction.bindEffectorDirectionService(effector);
    }

    public void on() {
	// ne rien faire
    }

    public void off() {
	// ne rien faire
    }

    // Exportation des services
    public ReceiverService exportReceiverService() {
	return (ReceiverService) receiver;
    }

    // Laison des fournisseurs
    // aucun fournisseur

}

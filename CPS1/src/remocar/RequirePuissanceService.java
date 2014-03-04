package remocar;

import tamago.ServiceBindException;

public interface RequirePuissanceService {

	void bindPuissanceService(PuissanceService puissance) throws ServiceBindException;
	
}

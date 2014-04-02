package contracts;

import contracts.base.PostconditionError;
import contracts.base.PreconditionError;
import services.ObjetService;
import decorators.ObjetDecorator;

public class ObjetContract extends ObjetDecorator {

	public ObjetContract(ObjetService obj) {
		super(obj);
	}

	public void init(String nom) {
		if (!(nom != ""))
			throw new PreconditionError("objet -> init");

		super.init(nom);

		if (!(nom == nom()))
			throw new PostconditionError("objet -> init");
	}

}

package contracts;

import services.ObjetMarchandService;
import contracts.base.PostconditionError;
import contracts.base.PreconditionError;
import decorators.ObjetMarchandDecorator;

public class ObjetMarchandContract extends ObjetMarchandDecorator {

	public ObjetMarchandContract(ObjetMarchandService obj) {
		super(obj);
	}

	public void init(String nom, int prix) {
		if (!(nom != "" && prix > 0))
			throw new PreconditionError("objetMarchand -> init");

		super.init(nom, prix);

		if (!(nom == nom()))
			throw new PostconditionError("objetMarchand -> init");
	}

	public void vendre() {
		if (!(!estVendu()))
			throw new PreconditionError("objetMarchand -> vendre");

		super.vendre();

		if (!(estVendu()))
			throw new PostconditionError("objetMarchand -> vendre");
	}
}

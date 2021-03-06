package contracts;

import services.ObjetEquipableService;
import contracts.base.PostconditionError;
import contracts.base.PreconditionError;
import decorators.ObjetEquipableDecorator;

public class ObjetEquipableContract extends ObjetEquipableDecorator {

	public ObjetEquipableContract(ObjetEquipableService objE) {
		super(objE);
	}

	public void init(String nom, int b) {
		if (!(nom != "" && b > 0))
			throw new PreconditionError("objet -> init");

		super.init(nom, b);

		if (!(nom == nom() && bonus() == b && !estPorte()))
			throw new PostconditionError("objet -> init");
	}

	public void init(String nom) {
		if (!(nom != ""))
			throw new PreconditionError("objet -> init");

		super.init(nom);

		if (!(nom == nom() && !estPorte()))
			throw new PostconditionError("objet -> init");
	}

	public void estRamasse() {
		if (!(!estPorte()))
			throw new PreconditionError("chose -> estRamsse");
		super.estRamasse();
		if (!(estPorte()))
			throw new PostconditionError("chose -> estRamasse");
	}

	public void estJete() {
		if (!(estPorte()))
			throw new PreconditionError("chose -> estJete");
		super.estJete();
		if (!(!estPorte()))
			throw new PostconditionError("chose -> estJete");
	}
}

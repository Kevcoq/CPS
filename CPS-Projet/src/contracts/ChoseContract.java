package contracts;

import contracts.base.PostconditionError;
import contracts.base.PreconditionError;
import services.ChoseService;
import decorators.ChoseDecorator;

public class ChoseContract extends ChoseDecorator {

	public ChoseContract(ChoseService chose) {
		super(chose);
	}

	public void init(int b) {
		super.init(b);
		if (!(bonus() == b) && !estPorte()) {
			throw new PostconditionError("chose -> init");
		}
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

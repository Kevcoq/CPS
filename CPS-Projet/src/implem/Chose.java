package implem;

import services.ChoseService;

public abstract class Chose implements ChoseService {
	private int bonus;
	private boolean estPorte;

	@Override
	public int bonus() {
		return bonus;
	}

	@Override
	public boolean estPorte() {
		return estPorte;
	}

	@Override
	public void init(int b) {
		bonus = b;
		estPorte = false;
	}

	@Override
	public void estRamasse() {
		estPorte = true;
	}

	@Override
	public void estJete() {
		estPorte = false;
	}

}

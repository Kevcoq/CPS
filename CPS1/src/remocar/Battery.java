package remocar;

import java.util.List;
import java.util.ArrayList;
import tamago.BasicComponent;
import tamago.ServiceBindException;

public class Battery extends BasicComponent implements PuissanceService,
		RequirePuissanceService {

	private int puiss;
	private List<PuissanceService> batteries;

	
	
	public Battery(int x) {
		super();
		this.puiss = x;
		batteries = new ArrayList<>();
	}
	
	public Battery () {
		this(10);
	}

	@Override
	public int getPuissance() {
		int somme = puiss;

		for (PuissanceService ps : batteries) {
			somme += ps.getPuissance();
		}
		return somme;
	}

	@Override
	public boolean use(int x) {
		boolean res = false;
		int tmp = getPuissance();

		System.out.println("Puissance : " + tmp);
		if (tmp > x) {
			for (int i = batteries.size() - 1; i >= 0 && !res; i--) {
				res = batteries.get(i).use(x);
			}
			if (!res && x < puiss) {
				puiss -= x;
				res = true;
			}
		}
		return res;
	}

	@Override
	public void bindPuissanceService(PuissanceService puissance)
			throws ServiceBindException {
		batteries.add(puissance);
	}

}

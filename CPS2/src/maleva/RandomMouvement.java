package maleva;

import tamago.*;

import java.util.*;

public class RandomMouvement extends ComportementAgent {

	/* fields */
	private Random rand;

	/* properties */
	private int angle_step = 10;

	public RandomMouvement(Agent outer) {
		super(outer);
		rand = new Random();
	}

	/* LCStepperController */

	public boolean step() throws LifeCycleException {
		// <<<< A VOUS D'ECRIRE LA METHODE >>>>

		action = new Action(Action.MOVE, outer.getAngle()
				+ (rand.nextInt(angle_step) * 2 - angle_step));
		return true; // step is finished
	}
}

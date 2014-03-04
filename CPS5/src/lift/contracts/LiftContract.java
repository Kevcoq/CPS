package lift.contracts;

import lift.decorators.LiftDecorator;
import lift.services.DoorStatus;
import lift.services.LiftService;
import lift.services.LiftStatus;

public class LiftContract extends LiftDecorator {

	public LiftContract(LiftService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		// inv: getLevel() >= getMinLevel()
		if (!(getLevel() >= getMinLevel())) {
			throw new InvariantError(
					"L'étage courant n'est pas supérieur ou égal à l'étage minimum");
		}
		// inv: getLevel() <= getMaxLevel()
		if (!(getLevel() <= getMaxLevel())) {
			throw new InvariantError(
					"L'étage courant n'est pas inférieur ou égal à l'étage maximum");
		}
		// inv: if getLiftStatus() \in { MOVING_UP, MOVING_DOWN }
		// then getDoorStatus() == CLOSED
		// else if getLiftStatus() == IDLE
		// then getDoorStatus() == OPENED
		if (getLiftStatus() == LiftStatus.MOVING_UP
				|| getLiftStatus() == LiftStatus.MOVING_DOWN) {
			if (!(getDoorStatus() == DoorStatus.CLOSED)) {
				throw new InvariantError(
						"La porte n'est pas fermée pendant le déplacement");
			}
		}/* else if (getLiftStatus() == LiftStatus.IDLE) {
			if (!(getDoorStatus() == DoorStatus.OPENED)) {
				throw new InvariantError(
						"La porte n'est pas ouverte pendant l'attente");
			}
		}*/
	}

	@Override
	public void init(Integer minLevel, Integer maxLevel) {
		// pre: minLevel >= 0
		if (!(minLevel >= 0)) {
			throw new PreconditionError("L'étage minimum n'est pas positif");
		}

		// pre: minLevel < maxLevel
		if (!(minLevel < maxLevel)) {
			throw new PreconditionError(
					"L'étage maximum n'est pas supérieur au minimum");
		}

		// inv pre
		checkInvariant();

		// run
		super.init(minLevel, maxLevel);

		// inv post
		checkInvariant();

		// post: getMinLevel() == minLevel
		if (!(getMinLevel() == minLevel)) {
			throw new PostconditionError(
					"L'étage minimal est incorrectement initialisé");
		}

		// post: getMaxLevel() == maxLevel
		if (!(getMaxLevel() == maxLevel)) {
			throw new PostconditionError(
					"L'étage maximal est incorrectement initialisé");
		}

		// post: getLevel() == minLevel
		if (!(getLevel() == minLevel)) {
			throw new PostconditionError(
					"L'étage courant est incorrectement initialisé à l'étage minimal");
		}

		// post: getLiftStatus() == IDLE
		if (!(getLiftStatus() == LiftStatus.IDLE)) {
			throw new PostconditionError(
					"L'ascenseur n'est pas en attente à l'initialisation");
		}

		// post: getDoorStatus() == OPENED
		if (!(getDoorStatus() == DoorStatus.OPENED)) {
			throw new PostconditionError(
					"La porte n'est pas ouverte à l'initialisation");
		}

		// // post: getCommands().init()
		// // post: getCommands().getNbUpCommands() == 0
		// if (!(getCommands().getNbUpCommands() == 0)) {
		// throw new PostconditionError(
		// "Il y a des commandes de montée à l'initialisation");
		// }
		//
		// // post: getCommands().getNbDownCommands() == 0
		// if (!(getCommands().getNbDownCommands() == 0)) {
		// throw new PostconditionError(
		// "Il y a des commandes de descente à l'initialisation");
		// }

	}

	@Override
	public void beginMoveUp() {
		// pre: getDoorStatus() == CLOSED
		if (!(getDoorStatus() == DoorStatus.CLOSED)) {
			throw new PreconditionError(
					"La porte n'est pas fermée avant un déplacement vers le haut");
		}
		// pre: getLiftStatus() == STANDBY_UP
		if (!(getLiftStatus() == LiftStatus.STANDBY_UP)) {
			throw new PreconditionError("L'ascenseur n'est pas prêt en montée");
		}

		// pre: getLevel() < getCommands().getNextUpCommand()
		if (!(getLevel() < getCommands().getNextUpCommand())) {
			throw new PreconditionError(
					"L'étage courant n'est pas strictement inférieur à la commande suivante");
		}

		// inv pre
		checkInvariant();

		// run
		super.beginMoveUp();

		// inv post
		checkInvariant();

		// post: getLiftStatus() == MOVING_UP
		if (!(getLiftStatus() == LiftStatus.MOVING_UP)) {
			throw new PostconditionError("L'ascenseur ne monte pas");
		}

	}

	@Override
	public void stepMoveUp() {
		// pre: getDoorStatus() == CLOSED
		if (!(getDoorStatus() == DoorStatus.CLOSED)) {
			throw new PreconditionError(
					"La porte n'est pas fermée pendant un déplacement vers le haut");
		}
		// pre: getLiftStatus() == MOVING_UP
		if (!(getLiftStatus() == LiftStatus.MOVING_UP)) {
			throw new PreconditionError("L'ascenseur ne monte pas");
		}
		// pre: getLevel() < getCommands().getNextUpCommand()
		if (!(getLevel() < getCommands().getNextUpCommand())) {
			throw new PreconditionError(
					"L'étage courant n'est pas strictement inférieur à la commande suivante");
		}

		// captures
		int getLevel_atPre = getLevel();

		// inv pre
		checkInvariant();

		// run
		super.stepMoveUp();

		// inv post
		checkInvariant();

		// post: getLevel() == getLevel()@pre + 1
		if (!(getLevel() == getLevel_atPre + 1)) {
			throw new PostconditionError("L'ascenseur n'est pas monté");
		}
	}

	@Override
	public void endMoveUp() {
		// pre: getDoorStatus() == CLOSED
		if (!(getDoorStatus() == DoorStatus.CLOSED)) {
			throw new PreconditionError(
					"La porte n'est pas fermée en fin de déplacement vers le haut");
		}

		// pre: getLiftStatus() == MOVING_UP
		if (!(getLiftStatus() == LiftStatus.MOVING_UP)) {
			throw new PreconditionError("L'ascenseur ne monte pas");
		}

		// pre: getLevel() == getCommands().getNextUpCommand()
		if (!(getLevel() == getCommands().getNextUpCommand())) {
			throw new PreconditionError(
					"L'étage courant n'est pas égal à la commande suivante");
		}

		// inv pre
		checkInvariant();

		// run
		super.endMoveUp();

		// inv post
		checkInvariant();

		// post: getLiftStatus() == STOP_UP
		if (!(getLiftStatus() == LiftStatus.STOP_UP)) {
			throw new PostconditionError("L'ascenseur ne stoppe pas en montée");
		}

		// post: getCommands().endUpCommand();
		// TODO

	}

	@Override
	public void beginMoveDown() {
		// pre: getDoorStatus() == CLOSED
		if (!(getDoorStatus() == DoorStatus.CLOSED)) {
			throw new PreconditionError(
					"La porte n'est pas fermée avant un déplacement vers le bas");
		}
		// pre: getLiftStatus() == STANDBY_DOWN
		if (!(getLiftStatus() == LiftStatus.STANDBY_DOWN)) {
			throw new PreconditionError(
					"L'ascenseur n'est pas prét en descente");
		}

		// pre: getLevel() > getCommands().getNextDownCommand()
		if (!(getLevel() > getCommands().getNextDownCommand())) {
			throw new PreconditionError(
					"L'étage courant n'est pas strictement supérieur à la commande suivante");
		}

		// inv pre
		checkInvariant();

		// run
		super.beginMoveUp();

		// inv post
		checkInvariant();

		// post: getLiftStatus() == MOVING_DOWN
		if (!(getLiftStatus() == LiftStatus.MOVING_DOWN)) {
			throw new PostconditionError("L'ascenseur ne descend pas");
		}

	}

	@Override
	public void stepMoveDown() {
		// pre: getDoorStatus() == CLOSED
		if (!(getDoorStatus() == DoorStatus.CLOSED)) {
			throw new PreconditionError(
					"La porte n'est pas fermée pendant un déplacement vers le bas");
		}
		// pre: getLiftStatus() == MOVING_DOWN
		if (!(getLiftStatus() == LiftStatus.MOVING_DOWN)) {
			throw new PreconditionError("L'ascenseur ne descend pas");
		}
		// pre: getLevel() > getCommands().getNextDownCommand()
		if (!(getLevel() > getCommands().getNextDownCommand())) {
			throw new PreconditionError(
					"L'étage courant n'est pas strictement supérieur à la commande suivante");
		}

		// captures
		int getLevel_atPre = getLevel();

		// inv pre
		checkInvariant();

		// run
		super.stepMoveDown();

		// inv post
		checkInvariant();

		// post: getLevel() == getLevel()@pre - 1
		if (!(getLevel() == getLevel_atPre - 1)) {
			throw new PostconditionError("L'ascenseur n'est pas descendu");
		}
	}

	@Override
	public void endMoveDown() {
		// pre: getDoorStatus() == CLOSED
		if (!(getDoorStatus() == DoorStatus.CLOSED)) {
			throw new PreconditionError(
					"La porte n'est pas fermée en fin de déplacement vers le bas");
		}

		// pre: getLiftStatus() == MOVING_DOWN
		if (!(getLiftStatus() == LiftStatus.MOVING_DOWN)) {
			throw new PreconditionError("L'ascenseur ne descend pas");
		}

		// pre: getLevel() == getCommands().getNextDownCommand()
		if (!(getLevel() == getCommands().getNextDownCommand())) {
			throw new PreconditionError(
					"L'étage courant n'est pas égal à la commande suivante");
		}

		// inv pre
		checkInvariant();

		// run
		super.endMoveDown();

		// inv post
		checkInvariant();

		// post: getLiftStatus() == STOP_UP
		if (!(getLiftStatus() == LiftStatus.STOP_DOWN)) {
			throw new PostconditionError(
					"L'ascenseur ne stoppe pas en descente");
		}

		// post: getCommands().endDownCommand();
		// TODO

	}

	@Override
	public void openDoor() {
		// pre: getDoorStatus() == CLOSED
		if (!(getDoorStatus() == DoorStatus.CLOSED)) {
			throw new PreconditionError("La porte n'est pas fermée");
		}

		// pre: getLiftStatus() \in { IDLE, STOP_UP, STOP_DOWN }
		if (!(getLiftStatus() == LiftStatus.IDLE
				|| getLiftStatus() == LiftStatus.STOP_UP || getLiftStatus() == LiftStatus.STOP_DOWN)) {
			throw new PreconditionError(
					"L'ascenseur n'est ni en attente, ni en arrêt");
		}

		// inv pre
		checkInvariant();

		// run
		super.openDoor();

		// inv post
		checkInvariant();

		// post: getDoorStatus() == OPENING
		if (!(getDoorStatus() == DoorStatus.OPENING)) {
			throw new PostconditionError(
					"La porte n'est pas en cours d'ouverture");
		}
	}

	@Override
	public void closeDoor() {
		// pre: getDoorStatus() == OPENED
		if (!(getDoorStatus() == DoorStatus.OPENED)) {
			throw new PreconditionError("La porte n'est pas ouverte");
		}
		// pre: getLiftStatus() \in { IDLE, STANDBY_UP, STANDBY_DOWN }
		if (!(getLiftStatus() == LiftStatus.IDLE
				|| getLiftStatus() == LiftStatus.STANDBY_UP || getLiftStatus() == LiftStatus.STANDBY_DOWN)) {
			throw new PreconditionError(
					"L'ascenseur n'est ni en attente, ni en arrêt");
		}

		// inv pre
		checkInvariant();

		// run
		super.closeDoor();

		// inv post
		checkInvariant();

		// post: getDoorStatus() == CLOSING
		if (!(getDoorStatus() == DoorStatus.CLOSING)) {
			throw new PostconditionError("La porte ne se ferme pas");
		}

	}

	@Override
	public void doorAck() {
		// pre: getDoorSTatus() \in { OPENING, CLOSING }
		if (!(getDoorStatus() == DoorStatus.OPENING || getDoorStatus() == DoorStatus.CLOSING)) {
			throw new PreconditionError(
					"La porte n'est ni en cours d'ouverture ni en cours de fermeture");
		}

		// captures
		LiftStatus getLiftStatus_atPre = getLiftStatus();
		DoorStatus getDoorStatus_atPre = getDoorStatus();

		// inv pre
		checkInvariant();

		// run
		super.doorAck();

		// inv post
		checkInvariant();

		// post: if getDoorStatus()@pre==OPENING then getDoorStatus()==OPENED
		// else if getDoorStatus()@pre==CLOSING then getDoorStatus()=CLOSED
		if (getDoorStatus_atPre == DoorStatus.OPENING) {
			if (!(getDoorStatus() == DoorStatus.OPENED)) {
				throw new PostconditionError("La porte ne s'est pas ouverte");
			}
		} else {
			if (!(getDoorStatus() == DoorStatus.CLOSED)) {
				throw new PostconditionError("La porte ne s'est pas fermée");
			}
		}

		// post: if getLiftStatus()@pre == IDLE then
		// then if getCommands().getNbDownCommands() > 0
		// then getLiftStatus() == STANDBY_DOWN
		// else if getCommands().getNbUpCommands() > 0
		// then getLiftStatus() == STANDBY_UP
		// else getLiftStatus() == IDLE
		// else getLiftStatus() == getLiftStatus()@pre
		if (getLiftStatus_atPre == LiftStatus.IDLE) {
			if (getCommands().getNbDownCommands() > 0) {
				if (!(getLiftStatus() == LiftStatus.STANDBY_DOWN)) {
					throw new PostconditionError(
							"L'ascenseur n'est pas prêt en descente");
				}
			} else if (getCommands().getNbUpCommands() > 0) {
				if (!(getLiftStatus() == LiftStatus.STANDBY_UP)) {
					throw new PostconditionError(
							"L'ascenseur n'est pas prêt en montée");
				}
			} else {
				if (!(getLiftStatus() == LiftStatus.IDLE)) {
					throw new PostconditionError(
							"L'ascenseur n'est pas en attente");
				}
			}
		} else {
			if (!(getLiftStatus() == getLiftStatus_atPre)) {
				throw new PostconditionError(
						"L'état de l'ascenseur n'aurait pas du changer");
			}
		}

	}

	@Override
	public void selectLevel(int level) {
		// pre: level >= getMinLevel()
		if (!(level >= getMinLevel())) {
			throw new PreconditionError(
					"L'étage choisi est strictement inférieur é l'étage minimal");
		}
		// pre: level <= getMaxLevel()
		if (!(level <= getMaxLevel())) {
			throw new PreconditionError(
					"L'étage choisi est strictement supérieur à l'étage maximal");
		}
		// pre: getLiftStatus() \in { IDLE, STANDBY_UP, STANDBY_DOWN }
		if (!(getLiftStatus() == LiftStatus.IDLE
				|| getLiftStatus() == LiftStatus.STANDBY_UP || getLiftStatus() == LiftStatus.STANDBY_DOWN)) {
			throw new PreconditionError(
					"L'ascenseur n'est ni en attente, ni prêt en montée ou en descente");
		}

		// inv pre
		checkInvariant();

		// run
		super.selectLevel(level);

		// inv post
		checkInvariant();

		// post: if level > getLevel()
		// then getCommands().addUpCommand(level)
		// else if level < getLevel()
		// then getCommands().addDownCommand(level)
		// TODO
	}

}

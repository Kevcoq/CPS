package lift.test;

import junit.framework.Assert;
import lift.contracts.PreconditionError;
import lift.services.CommandsService;
import lift.services.DoorStatus;
import lift.services.LiftService;
import lift.services.LiftStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractLiftTest {
	private LiftService lift;
	private CommandsService commands;

	protected AbstractLiftTest() {
		lift = null;
		commands = null;
	}

	protected final LiftService getLift() {
		return lift;
	}

	protected final CommandsService getCommands() {
		return commands;
	}

	protected final void setLift(LiftService lift) {
		this.lift = lift;
	}

	protected final void setCommands(CommandsService commands) {
		this.commands = commands;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		lift = null;
		commands = null;
	}

	public boolean checkInvariant() {
		boolean rep = lift.getMinLevel() <= lift.getLevel()
				&& lift.getLevel() <= lift.getMaxLevel();
		if ((lift.getLiftStatus() == LiftStatus.MOVING_UP || lift
				.getLiftStatus() == LiftStatus.MOVING_DOWN))
			rep &= lift.getDoorStatus() == DoorStatus.CLOSED;
		return rep;
	}

	// Exercice 1
	@Test
	public void testInit() {
		lift.init(0, 10);
		Assert.assertTrue(true);
	}

	@Test
	public void testInitFail1() {
		try {
			lift.init(-1, 10);
		} catch (PreconditionError e) {
			Assert.assertTrue(e.getMessage().equals(
					"Precondition failed: L'étage minimum n'est pas positif"));
		}
	}

	@Test
	public void testInitFail2() {
		try {
			lift.init(20, 10);
		} catch (PreconditionError e) {
			Assert.assertTrue(e
					.getMessage()
					.equals("Precondition failed: L'étage maximum n'est pas supérieur au minimum"));
		}
	}

	@Test
	public void testInitFail3() {
		try {
			lift.init(-1, -4);
		} catch (PreconditionError e) {
			Assert.assertTrue(e.getMessage().equals(
					"Precondition failed: L'étage minimum n'est pas positif"));
		}
	}

	@Test
	public void testOpenDoor() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.doorAck();
		lift.openDoor();
		Assert.assertTrue(true);
	}

	@Test
	public void testOpenDoorFail1() {
		lift.init(0, 10);
		try {
			lift.openDoor();
		} catch (PreconditionError e) {
			Assert.assertTrue(e.getMessage().equals(
					"Precondition failed: La porte n'est pas fermée"));
		}
		System.out.println(checkInvariant());
	}

	@Test
	public void testOpenDoorFail2() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(1);
		lift.doorAck();
		lift.beginMoveUp();
		try {
			lift.openDoor();
		} catch (PreconditionError e) {
			Assert.assertTrue(e
					.getMessage()
					.equals("Precondition failed: L'ascenseur n'est ni en attente, ni en arrêt"));
		}

		// faux & faux inatteigniable
	}

	@Test
	public void testCloseDoor() {
		lift.init(0, 10);
		lift.closeDoor();
		Assert.assertTrue(true);
	}

	@Test
	public void testCloseDoorFail1() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.doorAck();
		try {
			lift.closeDoor();
		} catch (PreconditionError e) {
			Assert.assertTrue(e.getMessage().equals(
					"Precondition failed: La porte n'est pas ouverte"));
		}
	}

	@Test
	public void testCloseDoorFail2() {
		Assert.assertTrue(true);
		// vrai & faux inatteigniable
	}

	@Test
	public void testCloseDoorFail3() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(1);
		lift.doorAck();
		lift.beginMoveUp();
		try {
			lift.closeDoor();
		} catch (PreconditionError e) {
			Assert.assertTrue(e.getMessage().equals(
					"Precondition failed: La porte n'est pas ouverte"));
		}
	}

	@Test
	public void testDoorAck() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.doorAck();
		Assert.assertTrue(true);
	}

	@Test
	public void testDoorAckFail() {
		lift.init(0, 10);
		try {
			lift.doorAck();
		} catch (PreconditionError e) {
			Assert.assertTrue(e
					.getMessage()
					.equals("Precondition failed: La porte n'est ni en cours d'ouverture ni en cours de fermeture"));
		}
	}

	// TODO : selectLevel

	// Exercice 2
	@Test
	public void testPostInit() {
		lift.init(0, 10);
		Assert.assertTrue(checkInvariant() && lift.getMinLevel() == 0
				&& lift.getMaxLevel() == 10
				&& lift.getLevel() == lift.getMinLevel()
				&& lift.getLiftStatus() == LiftStatus.IDLE
				&& lift.getDoorStatus() == DoorStatus.OPENED
				&& lift.getCommands() != null);
	}

	@Test
	public void testBeginMoveUp() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(1);
		lift.doorAck();
		lift.beginMoveUp();
		Assert.assertTrue(checkInvariant()
				&& lift.getLiftStatus() == LiftStatus.MOVING_UP);
	}

	@Test
	public void testStepMoveUp() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(1);
		lift.doorAck();
		lift.beginMoveUp();
		int level = lift.getLevel();
		lift.stepMoveUp();
		Assert.assertTrue(checkInvariant() && lift.getLevel() == level + 1);
	}

	@Test
	public void testEndMoveUp() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(1);
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		Assert.assertTrue(checkInvariant()
				&& lift.getLiftStatus() == LiftStatus.STOP_UP);
	}

	@Test
	public void testBeginMoveDown() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(1);
		lift.doorAck();
		lift.selectLevel(0);
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.closeDoor();
		lift.doorAck();
		lift.beginMoveDown();
		Assert.assertTrue(checkInvariant()
				&& lift.getLiftStatus() == LiftStatus.MOVING_DOWN);
	}

	@Test
	public void testStepMoveDown() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(1);
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.closeDoor();
		lift.selectLevel(0);
		lift.doorAck();
		lift.beginMoveDown();
		int level = lift.getLevel();
		lift.stepMoveDown();
		Assert.assertTrue(checkInvariant() && lift.getLevel() == level - 1);
	}

	@Test
	public void testEndMoveDown() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(1);
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		lift.openDoor();
		lift.doorAck();
		lift.closeDoor();
		lift.selectLevel(0);
		lift.doorAck();
		lift.beginMoveDown();
		lift.stepMoveDown();
		lift.endMoveDown();
		Assert.assertTrue(checkInvariant()
				&& lift.getLiftStatus() == LiftStatus.STOP_DOWN);
	}

	@Test
	public void testOpenDoorPost() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.doorAck();
		lift.openDoor();
		Assert.assertTrue(checkInvariant()
				&& lift.getDoorStatus() == DoorStatus.OPENING);
	}

	@Test
	public void testCloseDoorPost() {
		lift.init(0, 10);
		lift.closeDoor();
		Assert.assertTrue(checkInvariant()
				&& lift.getDoorStatus() == DoorStatus.CLOSING);
	}

	@Test
	public void testDoorAckPost() {
		lift.init(0, 10);
		lift.closeDoor();
		DoorStatus door = lift.getDoorStatus();
		LiftStatus status = lift.getLiftStatus();
		lift.doorAck();
		boolean rep = false;
		if (door == DoorStatus.OPENING)
			rep = lift.getDoorStatus() == DoorStatus.OPENED;
		else if (door == DoorStatus.CLOSING)
			rep = lift.getDoorStatus() == DoorStatus.CLOSED;

		if (status != LiftStatus.IDLE)
			rep &= lift.getLiftStatus() == LiftStatus.IDLE;

		Assert.assertTrue(checkInvariant() && rep);
	}

	// Exercice 3
	@Test
	public void testSedInBorne() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(3);
		lift.doorAck();
		lift.beginMoveUp();
		lift.stepMoveUp();
		lift.stepMoveUp();
		lift.stepMoveUp();
		lift.endMoveUp();
		Assert.assertTrue(lift.getLevel() == 3);
	}

	@Test
	public void testSedBorneMin() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(0);
		lift.doorAck();
		lift.openDoor();
		Assert.assertTrue(lift.getLevel() == 0);
	}

	@Test
	public void testSedBorneMax() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(10);
		lift.doorAck();
		lift.beginMoveUp();
		for (int i = 0; i < 10; i++)
			lift.stepMoveUp();
		lift.endMoveUp();
		Assert.assertTrue(lift.getLevel() == 10);
	}

	@Test
	public void testSedBorneMiddle() {
		lift.init(0, 10);
		lift.closeDoor();
		lift.selectLevel(5);
		lift.doorAck();
		lift.beginMoveUp();
		for (int i = 0; i < 5; i++)
			lift.stepMoveUp();
		lift.endMoveUp();
		Assert.assertTrue(lift.getLevel() == 5);
	}

	// TODO : exercice 3 hors borne

}

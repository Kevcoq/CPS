package lift.services;


public interface LiftService {
	/* Observators */
	
	public Integer getMinLevel();
	public Integer getMaxLevel();
	public Integer getLevel();
	
	public DoorStatus getDoorStatus();
	public LiftStatus getLiftStatus();
	
	public CommandsService getCommands();
	
	/* Invariants */
		
	// inv:  getLevel() >= getMinLevel()
	// inv:  getLevel() <= getMaxLevel()
	// inv:  if getLiftStatus() \in { MOVING_UP,  MOVING_DOWN }
	//       then getDoorStatus() == CLOSED
	//       else if getLiftStatus() == IDLE
	//       then getDoorStatus() == OPENED
	
	/* Initializers */
	
	/**
	 * pre: minLevel >= 0
	 * pre: minLevel < maxLevel
	 * post: getMinLevel() == minLevel
	 * post: getMaxLevel() == maxLevel
	 * post: getLevel() == minLevel
	 * post: getLiftStatus() == IDLE
	 * post: getDoorStatus() == OPENED
	 * post: getCommands().getNbUpCommands() == 0
	 * post: getCommands().getNbDownCommands() == 0
	 */
	public void init(Integer minLevel, Integer maxLevel);
	
	/* Operators */
	
	// pre: getDoorStatus() == CLOSED
	// pre: getLiftStatus() == STANDBY_UP
	// pre: getLevel() < getCommands().getNextUpCommand()
	// post: getLiftStatus() == MOVING_UP
	public void beginMoveUp();
	
	// pre: getDoorStatus() == CLOSED
	// pre: getLiftStatus() == MOVING_UP
	// pre: getLevel() < getCommands().getNextUpCommand()
	// post: getLevel() == getLeve()@pre + 1
	public void stepMoveUp();
	
	// pre: getDoorStatus() == CLOSED
	// pre: getLiftStatus() == MOVING_UP
	// pre: getLevel() == getCommands().getNextUpCommand()
	// post: getLiftStatus() == STOP_UP
	// post: getCommands().endUpCommand();
	public void endMoveUp();
	
	// pre: getDoorStatus() == CLOSED
	// pre: getLiftStatus() == STANDBY_DOWN
	// pre: getCommands().getNbDownCommands() > 0
	// post: getLiftStatus() == MOVING_DOWN
	public void beginMoveDown();
	
	// pre: getDoorStatus() == CLOSED
	// pre: getLiftStatus() == MOVING_DOWN
	// pre: getLevel() < getCommands().getNextCommand()
	// post: getLevel() == getLevel()@pre - 1
	public void stepMoveDown();
	
	
	// pre: getDoorStatus() == CLOSED
	// pre: getLiftStatus() == MOVING_DOWN
	// pre: getLevel() == getCommands().getNextCommand()
	// post: getLiftStatus() == STOP_DOWN
	// post: getCommands().endDownCommand();
	public void endMoveDown();

	// pre: getDoorStatus() == CLOSED
	// pre: getLiftStatus() \in { IDLE, STOP_UP, STOP_DOWN }
	// post: getDoorStatus() == OPENING
	public void openDoor();
	
	// pre: getDoorStatus() == OPENED
	// pre: getLiftStatus() \in { STANDBY_UP, STANDBY_DOWN }
	// post: getDoorStatus() == CLOSING
	public void closeDoor();
	
	// pre: getDoorSTatus() \in { OPENING, CLOSING }
	// post: if getDoorStatus()@pre==OPENING then getDoorStatus()==OPENED
	//       else getDoorStatus()=CLOSED
	// post: if getLiftStatus()@pre == IDLE then
	//       then if getCommands().getNbDownCommands() > 0
	//            then getLiftStatus() == STANDBY_DOWN
	//            else if getCommands().getNbUpCommands() > 0
	//                 then getLiftStatus() == STANDBY_UP
	//                 else getLiftStatus() == IDLE
	//       else if getLiftStatus()@pre \in { STOP_DOWN, STOP_UP}
	//            then getLiftStatus() == IDLE
	public void doorAck();
	
	// pre: level >= getMinLevel()
	// pre: level <= getMaxLevel()
	// pre: getLiftStatus() \in { IDLE, STANDBY_UP, STANDBY_DOWN }
	// post:  if level > getLevel()
	//        then getCommands().addUpCommand(level)
	//        else if level < getLevel()
	//        then getCommands().addDownCommand(level)
	//        else no change
	public void selectLevel(int level);
	
	
}

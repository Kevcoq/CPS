package lift.services;


public interface CommandsService {
	/* Observators */
	
	// pre: getNbUpCommands() > 0
	public Integer getNextUpCommand();
	
	// pre: index >= 0  &&  index < getNbUpCommands()
	public Integer getUpCommand(int index);
	public int getNbUpCommands();

	// pre: cmd >= 0
	public boolean hasUpCommand(Integer cmd);
	
	// pre: getNbDownCommands() > 0
	public Integer getNextDownCommand();
	// pre: index >= 0  &&  index < getNbDownCommands()
	public Integer getDownCommand(int index);
	public int getNbDownCommands();

	// pre: cmd >= 0
	public boolean hasDownCommand(Integer cmd);

	/* Invariant */
	// inv: hasUpCommand(Integer cmd) 
	//      ==  \exists i \in [0..getNbUpCommands()-1] { getUpCommand(i) == cmd }
	// inv: hasDownCommand(Integer cmd) 
	//      ==  \exists i \in [0..getNbDownCommands()-1] { getDownCommand(i) == cmd }
	// inv: forall i:Integer \in [0..getNbUpCommands()-1] {
	//          getUpCommand(i) < getUpCommand(i+1)
	//      }
	// inv: forall i:Integer \in [0..getNbDownCommands()-1] {
	//          getDownCommand(i) > getDownCommand(i+1)
	//      }
	// inv: getNextUpCommand() == getUpCommand(0)
	// inv: getNextDownCommand() == getDownCommand(0)
	
	/* Initializers */
	
	// post: getNbUpCommands() == 0
	// post: getNbDownCommands() == 0
	public void init();
	
	/* Opertors */
	
	/**
	 * pre: cmd >= 0
	 * pre: !hasUpCommand(cmd)
	 * post: getNbUpCommands() == getNbUpCommands()@pre + 1
	 * post: \exists j: Integer \in [0..getNbUpCommands()] {
	 *           \forall i:Integer \in [0..j-1] {
	 *                getNbUpCommand(i) == getNbUpCommand(i)@pre
	 *                && getNbUpCommand(i) < getNbUpCommand(i+1)
	 *           } && getNbUpCommand(j) == cmd
	 *        && \forall k:Integer \in [j..getNbUpCommands()-2] {
	 *                getNbUpCommand(k+1) == getNbUpCommand(k)@pre
	 *                && getNbUpCommand(k) <  getNbUpCommand(k+1)
	 *            }
	 *       }
	 */
	public void addUpCommand(Integer cmd);

	/**
	 * pre: cmd >= 0
	 * pre: !hasDownCommand(cmd)
	 * post: getNbDownCommands() == getNbDownCommands()@pre + 1
	 * post: \exists j: Integer \in [0..getNbDownCommands()] {
	 *           \forall i:Integer \in [0..j-1] {
	 *                getNbDownCommand(i) == getNbDownCommand(i)@pre
	 *                && getNbDownCommand(i) > getNbDownCommand(i+1)
	 *           } && getNbDownCommand(j) == cmd
	 *        && \forall k:Integer \in [j..getNbDownCommands()-2] {
	 *                getNbDownCommand(k+1) == getNbDownCommand(k)@pre
	 *                && getNbDownCommand(k) >  getNbDownCommand(k+1)
	 *            }
	 *       }
	 */
	 public void addDownCommand(Integer cmd);

	 /**
	  * pre: getNbUpCommands() > 0
	  * post: getNbUpCommands() == getNbUpCommands()@pre - 1
	  * post: \forall i:Integer \in [0..getNbUpCommands()-1] {
	  *          getUpCommand(i) == getUpCommand(i+1)@pre
	  *       }
	  */
	public void endUpCommand();
	
	 /**
	  * pre: getNbDownCommands() > 0
	  * post: getNbDownCommands() == getNbDownCommands()@pre - 1
	  * post: \forall i:Integer \in [0..getNDownCommands()-1] {
	  *          getDownCommand(i) == getDownCommand(i+1)@pre
	  *       }
	  */
	public void endDownCommand();
}

package lift.contracts;

import lift.decorators.CommandsDecorator;
import lift.services.CommandsService;

public class CommandsContract extends CommandsDecorator {
	
	public CommandsContract(CommandsService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		// inv: hasUpCommand(Integer cmd) 
		//      ==  \exists i \in [0..getNbUpCommands()-1] { getUpCommand(i) == cmd }
		// TODO
		
		// inv: hasDownCommand(Integer cmd) 
		//      ==  \exists i \in [0..getNbDownCommands()-1] { getDownCommand(i) == cmd }	
		// TODO
		
		// inv: forall i:Integer \in [0..getNbUpCommands()-1] {
		//          getUpCommand(i) < getUpCommand(i+1)
		//      }
		for(int i=0;i<getNbUpCommands()-1;i++) {
			if(!(getUpCommand(i) < getUpCommand(i+1))) {
				throw new InvariantError("Les commands de montée ne sont pas strictement croissantes");
			}
		}
		
		// inv: forall i:Integer \in [0..getNbDownCommands()-1] {
		//          getDownCommand(i) > getDownCommand(i+1)
		//      }
		for(int i=0;i<getNbDownCommands()-1;i++) {
			if(!(getDownCommand(i) > getDownCommand(i+1))) {
				throw new InvariantError("Les commands de descente ne sont pas strictement décroissantes");
			}
		}
		
		// inv: getNextUpCommand() == getUpCommand(0)
		if(getNbUpCommands() > 0) {
			if(!(getNextUpCommand() == getUpCommand(0))) {
				throw new InvariantError("La première commande de montée est invalide");
			}
		}
		
		// inv: getNextDownCommand() == getDownCommand(0)
		if(getNbDownCommands() > 0) {
			if(!(getNextDownCommand() == getDownCommand(0))) {
				throw new InvariantError("La première commande de descente est invalide");
			}
		}

	}
	
	
	@Override
	public Integer getNextUpCommand() {
		// pre: getNbUpCommands() > 0
		if(!(getNbUpCommands() > 0)) {
			throw new PreconditionError("Aucune commande de montée");
		}
		// run
		return super.getNextUpCommand();
	}

	@Override
	public Integer getNextDownCommand() {
		// pre: getNbDownCommands() > 0
		if(!(getNbDownCommands() > 0)) {
			throw new PreconditionError("Aucune commande de descente");
		}
		// run
		return super.getNextDownCommand();
	}

	@Override
	public Integer getUpCommand(int index) {
		// pre: index >= 0  &&  index < getNbUpCommands()
		if(!(index >= 0  &&  index < getNbUpCommands())) {
			throw new PreconditionError("Index hors bornes: "+index);
		}
		// run
		return super.getUpCommand(index);
	}

	@Override
	public Integer getDownCommand(int index) {
		// pre: index >= 0  &&  index < getNbDownCommands()
		if(!(index >= 0  &&  index < getNbDownCommands())) {
			throw new PreconditionError("Index hors bornes: "+index);
		}
		// run
		return super.getDownCommand(index);
	}
	
	@Override
	public boolean hasUpCommand(Integer cmd) {
		// pre: cmd >= 0
		if(!(cmd>=0)) {
			throw new PreconditionError("La command est négative");
		}
		// run
		return super.hasUpCommand(cmd);
	}

	@Override
	public boolean hasDownCommand(Integer cmd) {
		// pre: cmd >= 0
		if(!(cmd>=0)) {
			throw new PreconditionError("La command est négative");
		}
		// run
		return super.hasDownCommand(cmd);
	}
	
	@Override
	public void init() {
		// inv@pre
		checkInvariant();
		// run
		super.init();
		// inv@post
		checkInvariant();
		// post: getNbUpCommands() == 0
		if(!(getNbUpCommands() == 0)) {
			throw new PostconditionError("Il y a des commandes en montée");
		}
		// post: getNbDownCommands() == 0
		if(!(getNbDownCommands() == 0)) {
			throw new PostconditionError("Il y a des commandes en descente");
		}
	}
	
	@Override
	public void addUpCommand(Integer cmd) {
		// pre: cmd >= 0
		if(!(cmd>=0)) {
			throw new PreconditionError("La command est négative");
		}		
		// pre: !hasUpCommand(cmd)
		if(!(!hasUpCommand(cmd))) {
			throw new PreconditionError("La commande de montée est déjà sélectionnée");
		}
		// captures
		int getNbUpCommands_atPre = getNbUpCommands();
		// inv@pre
		checkInvariant();
		// run
		super.addUpCommand(cmd);
		// inv@post
		checkInvariant();
		// post: getNbUpCommands() == getNbUpCommands()@pre + 1
		if(!(getNbUpCommands() == getNbUpCommands_atPre + 1)) {
			throw new PostconditionError("Le nombre de commandes de montée n'est pas incrémenté");
		}
		/* \exists j: Integer \in [0..getNbUpCommands()] {
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
		// TODO
	}

	@Override
	public void addDownCommand(Integer cmd) {
		// pre: cmd >= 0
		if(!(cmd>=0)) {
			throw new PreconditionError("La command est négative");
		}		
		// pre: !hasDownCommand(cmd)
		if(!(!hasDownCommand(cmd))) {
			throw new PreconditionError("La commande de descente est déjà sélectionnée");
		}
		// captures
		int getNbDownCommands_atPre = getNbDownCommands();
		// inv@pre
		checkInvariant();
		// run
		super.addDownCommand(cmd);
		// inv@post
		checkInvariant();
		// post: getNbDownCommands() == getNbDownCommands()@pre + 1
		if(!(getNbDownCommands() == getNbDownCommands_atPre + 1)) {
			throw new PostconditionError("Le nombre de commandes de descente n'est pas incrémenté");
		}
		/* post: \exists j: Integer \in [0..getNbDownCommands()] {
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
		// TODO
	}
	
	@Override
	public void endUpCommand() {
		// pre: getNbUpCommands() > 0
		if(!(getNbUpCommands() > 0)) {
			throw new PreconditionError("Il n'y a pas de commande de montée");
		}
		// captures
		int getNbUpCommands_atPre = getNbUpCommands();
		// inv@pre
		checkInvariant();
		// run
		super.endUpCommand();
		// inv@post
		checkInvariant();
		// post: getNbUpCommands() == getNbUpCommands()@pre - 1
		if(!(getNbUpCommands() == getNbUpCommands_atPre - 1)) {
			throw new PostconditionError("Le nombre de commandes de montée n'est pas décrémenté");
		}
		/* post: \forall i:Integer \in [0..getNbUpCommands()-1] {
		 *          getUpCommand(i) == getUpCommand(i+1)@pre
		 *       }
		 */
		// TODO
	}

	@Override
	public void endDownCommand() {
		// pre: getNbDownCommands() > 0
		if(!(getNbDownCommands() > 0)) {
			throw new PreconditionError("Il n'y a pas de commande de descente");
		}
		// captures
		int getNbDownCommands_atPre = getNbDownCommands();
		// inv@pre
		checkInvariant();
		// run
		super.endDownCommand();
		// inv@post
		checkInvariant();
		// post: getNbDownCommands() == getNbDownCommands()@pre - 1
		if(!(getNbDownCommands() == getNbDownCommands_atPre - 1)) {
			throw new PostconditionError("Le nombre de commandes de descente n'est pas décrémenté");
		}
		/* post: \forall i:Integer \in [0..getNbDownCommands()-1] {
		 *          getDownCommand(i) == getDownCommand(i+1)@pre
		 *       }
		 */
		// TODO
	}
}

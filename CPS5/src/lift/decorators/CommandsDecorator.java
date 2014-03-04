package lift.decorators;

import lift.services.CommandsService;

public class CommandsDecorator implements CommandsService {
	private final CommandsService delegate;
	
	public CommandsDecorator(CommandsService delegate) {
		this.delegate = delegate;
	}

	@Override
	public Integer getNextUpCommand() {
		return delegate.getNextUpCommand();
	}

	@Override
	public Integer getUpCommand(int index) {
		return delegate.getUpCommand(index);
	}

	@Override
	public int getNbUpCommands() {
		return delegate.getNbUpCommands();
	}

	@Override
	public boolean hasUpCommand(Integer cmd) {
		return delegate.hasUpCommand(cmd);
	}

	@Override
	public Integer getNextDownCommand() {
		return delegate.getNextDownCommand();
	}

	@Override
	public Integer getDownCommand(int index) {
		return delegate.getDownCommand(index);
	}

	@Override
	public int getNbDownCommands() {
		return delegate.getNbDownCommands();
	}

	@Override
	public boolean hasDownCommand(Integer cmd) {
		return delegate.hasDownCommand(cmd);
	}

	@Override
	public void init() {
		delegate.init();
	}

	@Override
	public void addUpCommand(Integer cmd) {
		delegate.addUpCommand(cmd);
	}

	@Override
	public void addDownCommand(Integer cmd) {
		delegate.addDownCommand(cmd);
	}

	@Override
	public void endUpCommand() {
		delegate.endUpCommand();
	}

	@Override
	public void endDownCommand() {
		delegate.endDownCommand();
	}
	
}

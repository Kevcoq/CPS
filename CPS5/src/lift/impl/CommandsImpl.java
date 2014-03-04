package lift.impl;

import lift.services.CommandsService;

public class CommandsImpl implements CommandsService {
	private CmdEntry upCommands;
	private int nbUpCommands;
	private CmdEntry downCommands;
	private int nbDownCommands;

	public CommandsImpl() {
		upCommands = null;
		nbUpCommands = -1;
		downCommands = null;
		nbDownCommands = -1;
	}

	public void init() {
		upCommands = new CmdEntry(-1, null);
		nbUpCommands = 0;
		downCommands = new CmdEntry(-1, null);
		nbDownCommands = 0;
	}

	@Override
	public int getNbUpCommands() {
		return nbUpCommands;
	}

	@Override
	public int getNbDownCommands() {
		return nbDownCommands;
	}

	@Override
	public boolean hasUpCommand(Integer cmd) {
		return searchCmd(upCommands.getNext(), cmd);
	}

	@Override
	public boolean hasDownCommand(Integer cmd) {
		return searchCmd(downCommands.getNext(), cmd);
	}

	@Override
	public Integer getUpCommand(int index) {
		return fetchCmd(upCommands, index);
	}

	@Override
	public Integer getDownCommand(int index) {
		return fetchCmd(downCommands, index);
	}

	@Override
	public void addUpCommand(Integer cmd) {
		insertSorted(upCommands, cmd, true);
		nbUpCommands++;
	}

	@Override
	public void addDownCommand(Integer cmd) {
		insertSorted(downCommands, cmd, false);
		nbDownCommands++;
	}

	@Override
	public Integer getNextUpCommand() {
		CmdEntry first = upCommands.getNext();
		if (first == null) {
			throw new Error("No first up command");
		}
		return first.getCmd();
	}

	@Override
	public Integer getNextDownCommand() {
		CmdEntry first = downCommands.getNext();
		if (first == null) {
			throw new Error("No first down command");
		}
		return first.getCmd();
	}

	@Override
	public void endUpCommand() {
		CmdEntry first = upCommands.getNext();
		if (first == null) {
			throw new Error("No first up command");
		}
		upCommands.setNext(first.getNext());
		nbUpCommands--;
	}

	@Override
	public void endDownCommand() {
		CmdEntry first = downCommands.getNext();
		if (first == null) {
			throw new Error("No first down command");
		}
		downCommands.setNext(first.getNext());
		nbDownCommands--;
	}

	public boolean searchCmd(CmdEntry start, int cmd) {
		CmdEntry entry = start;
		while (entry != null) {
			if (entry.getCmd() == cmd) {
				return true;
			}
			entry = entry.getNext();
		}
		return false;
	}

	public int fetchCmd(CmdEntry start, int index) {
		return fetchEntry(start, index).getCmd();
	}

	public CmdEntry fetchEntry(CmdEntry start, int index) {
		int i = 0;
		CmdEntry entry = start;
		while (i < index) {
			entry = entry.getNext();
			if (entry == null) {
				throw new IndexOutOfBoundsException();
			}
			i++;
		}
		return entry;
	}

	public void insertSorted(CmdEntry start, int cmd, boolean sorted) {
		CmdEntry prev = start;
		CmdEntry entry = prev.getNext();
		while (entry != null) {
			if (cmd == entry.getCmd()) {
				throw new IllegalStateException("Cannot insert '" + cmd
						+ "': already in list");
			}
			boolean cmp = false;
			if (sorted) {
				cmp = cmd < entry.getCmd();
			} else {
				cmp = cmd > entry.getCmd();
			}
			if (cmp) {
				CmdEntry newEntry = new CmdEntry(cmd, entry);
				prev.setNext(newEntry);
				return;
			}
			prev = entry;
			entry = entry.getNext();
		}
		prev.setNext(new CmdEntry(cmd, null)); // insert at the end
	}

	/* package */class CmdEntry {
		private int cmd;
		private CmdEntry next;

		public CmdEntry(int cmd, CmdEntry next) {
			this.cmd = cmd;
			this.next = next;
		}

		public int getCmd() {
			return cmd;
		}

		public CmdEntry getNext() {
			return next;
		}

		private void setNext(CmdEntry next) {
			this.next = next;
		}

	}

}
